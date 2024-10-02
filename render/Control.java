package jgui.render;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import jgui.Context;
import jgui.event.EventArguments;
import jgui.event.EventPreset;
import jgui.event.IEventCallback;
import jgui.event.IEventValidator;
import jgui.event.arguments.RenderEventArguments;
import jgui.event.arguments.ValidationEventArguments;

public abstract class Control {

    protected String id = null;

    //protected Cursor cursor = Cursor.DEFAULT; //todo idk how and idc dgf
    //protected Control owner = null;
    //protected Control parent = null;
    protected List<? extends Control> childs = null;
    protected Map<Object, Object> tags = null;
    protected Map<String, IEventCallback> events = null;

    protected int x = 0; //display x
    protected int y = 0; //display y
    protected int z = 0; //layer index

    protected int width = 0;
    protected int height = 0;
    protected int depth = 0;

    protected ViewPort viewPort = null;

    protected boolean loaded = false;
    protected boolean excluded = false;
    protected boolean visible = true;
    protected boolean active = false;
    protected boolean enabled = true;

    protected float opacity = 1.0f;
    protected float rotation = 0.0f;

    public Control() {
        return;
    }

    protected Control(String id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.viewPort = new ViewPort(x, y, x + width, y + height);

        setEventCallback(EventPreset.PRE_RENDER, Control::preRenderEvent);
        setEventCallback(EventPreset.POST_RENDER, Control::postRenderEvent);
    }

    private static boolean nonNull(Object object) {
        return object != null; //return Object::nonNull; //<- todo bc i dont have it right now and it gives me errors =(
    }

    public boolean isAvailable() {
        return loaded && !excluded;
    }

    public boolean isVisible() {
        return visible && opacity > 0.0f;
    }

    public boolean compareId(String id) {
        return this.id != null && this.id.equals(id);
    }

    public boolean toggle() {
        return excluded = !excluded;
    }

    public boolean swapVisible() {
        return visible = !visible;
    }

    public boolean swapActive() {
        return active = !active;
    }

    public boolean swapEnabled() {
        return enabled = !enabled;
    }

    public <K, V> void setTag(K key, V value) {
        if (tags == null) {
            tags = new HashMap<>();
        }

        tags.put(key, value);
    }

    public <K, V> V getTag(K key, V defaultValue, boolean force) {
        if (tags == null || tags.isEmpty()) {
            setTag(key, defaultValue);

            return defaultValue;
        }

        V result = (V) tags.getOrDefault(key, defaultValue);
        if (result == null) {
            setTag(key, result = defaultValue);
        }

        return result;
    }

    protected <T extends Control> boolean addChild(T element) {
        if (childs == null) {
            childs = new ArrayList<>();
        }

        if (childs.contains(childs)) {
            return false;
        }

        ((Collection) childs).add(element);

        return true;
    }

    protected void addChilds(Collection<? extends Control> elements) {
        if (childs == null) {
            childs = new ArrayList<>();
        }

        childs.removeIf(elements::contains);
        childs.addAll((Collection) elements);
    }

    public List<? extends Control> getChilds() {
        return childs;
    }

    public Control getChildById(String id, int depth) {
        for (Control control : childs) {
            if (control.compareId(id)) {
                return control;
            }

            if (depth > 0 || depth == -1) {
                Control result = control.getChildById(id, depth == -1 ? depth : (depth - 1));
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public void setEventCallback(String name, IEventCallback event) {
        if (events == null) {
            events = new HashMap<>();
        }

        events.put(name, event);
    }

    public void setEventCallback(EventPreset identifier, IEventCallback event) {
        setEventCallback(identifier.name(), event);
    }

    public Map<String, IEventCallback> getEventCallbacks() {
        return events;
    }

    public IEventCallback getEventByName(String name) {
        if (events == null || events.isEmpty()) {
            return null;
        }

        return events.getOrDefault(name, null);
    }

    public Object executeEvent(String event, Control sender, EventArguments arguments) {
        IEventCallback action = getEventByName(event);
        if (action == null) {
            return null;
        }

        boolean isRender = event.equals(EventPreset.RENDER.name());
        if (isRender) {
            IEventCallback pre = getEventByName(EventPreset.PRE_RENDER.name());
            if (pre != null) {
                pre.invoke(sender, arguments);

                if (arguments.isAbort()) {
                    return null;
                }
            }
        }

        Object result = action.invoke(sender, arguments);

        if (isRender) {
            IEventCallback post = getEventByName(EventPreset.POST_RENDER.name());
            if (post != null) {
                post.invoke(sender, arguments);
            }
        }

        return result;
    }

    public Object executeEvent(EventPreset event, Control sender, EventArguments arguments) {
        return executeEvent(event.name(), sender, arguments);
    }

    protected boolean executeEventChain(String event, EventArguments arguments, Predicate<Object> checker, int depth) {
        Object result = executeEvent(event, this, arguments);
        if (checker != null && !checker.test(result)) {
            return false;
        }

        if ((depth > 0 || depth == -1) && childs != null) {
            for (Control child : childs) {
                child.executeEventChain(event, arguments, checker, depth == -1 ? depth : (depth - 1));
            }
        }

        return true;
    }

    protected boolean executeEventChain(EventPreset event, EventArguments arguments, Predicate<Object> checker, int depth) {
        return executeEventChain(event.name(), arguments, checker, depth);
    }

    public boolean executeEventChain(String event, EventArguments arguments, boolean executeChilds) {
        return executeEventChain(event, arguments, Control::nonNull, executeChilds ? -1 : 0);
    }

    public boolean executeEventChain(EventPreset event, EventArguments arguments, Predicate<Object> checker, boolean executeChilds) {
        return executeEventChain(event.name(), arguments, checker, executeChilds ? -1 : 0);
    }

    public EventArguments validate(String event, IEventValidator validator, Predicate<Object> checker, int depth) {
        ValidationEventArguments validationArguments = new ValidationEventArguments(event);
        
        executeEvent(EventPreset.EVENT_VALIDATE, this, validationArguments);

        if (validationArguments.isAbort()) {
            return null;
        }

        EventArguments result = validator.validate(this, event);
        if (checker != null && !checker.test(result)) {
            return null;
        }

        if ((depth > 0 || depth == -1) && childs != null) {
            for (Control child : childs) {
                child.validate(event, validator, checker, depth == -1 ? depth : (depth - 1));
            }
        }

        return result;
    }

    public EventArguments validate(String event, IEventValidator validator, int depth) {
        return validate(event, validator, Control::nonNull, depth);
    }

    public EventArguments validate(String event, IEventValidator validator, boolean validateChilds) {
        return validate(event, validator, Control::nonNull, validateChilds ? -1 : 0);
    }

    public boolean load() {
        return loaded = executeEventChain(EventPreset.LOAD, new EventArguments(), Control::nonNull, true);
    }

    public boolean unload() {
        return loaded = !executeEventChain(EventPreset.UNLOAD, new EventArguments(), Control::nonNull, true);
    }

    public boolean update(boolean updateChilds) {
        return executeEventChain(EventPreset.UPDATE, new EventArguments(), Control::nonNull, updateChilds);
    }

    public boolean render(Context context) {
        if (!isAvailable() || !isVisible()) {
            return false;
        }

        RenderEventArguments arguments = new RenderEventArguments(context.getRootElement(), context.getRenderProvider());

        return executeEventChain(EventPreset.RENDER, arguments, Control::nonNull, true);
    }

    protected static Object preRenderEvent(Control sender, EventArguments arguments) {
        if (!(arguments instanceof RenderEventArguments)) {
            return null;
        }

        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        provider.pushViewPort(sender.viewPort, true);

        return null;
    }

    protected static Object postRenderEvent(Control sender, EventArguments arguments) {
        if (!(arguments instanceof RenderEventArguments)) {
            return null;
        }

        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        provider.popViewPort(true);

        return null;
    }
}
