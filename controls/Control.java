package jgui.controls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import jgui.event.EventArguments;
import jgui.event.IEvent;
import jgui.event.arguments.RenderEventArguments;
import jgui.render.RenderProvider;

public abstract class Control {

    protected String id = null;

    //protected Cursor cursor = Cursor.DEFAULT; //todo idk how and idc dgf
    //protected Control owner = null;
    //protected Control parent = null;
    protected List<? extends Control> childs = null;
    protected Map<Object, Object> tags = null;
    protected Map<String, IEvent> events = null;

    protected int x = 0; //display x
    protected int y = 0; //display y
    protected int z = 0; //layer index

    protected int width = 0;
    protected int height = 0;
    protected int depth = 0;

    protected boolean loaded = false;
    protected boolean excluded = false;
    protected boolean visible = true;
    protected boolean active = false;
    protected boolean enabled = true;

    protected float opacity = 1.0f;
    protected float rotation = 0.0f;

    protected Control(String id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    protected boolean addChild(Control element) {
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

    protected boolean compareId(String id) {
        return this.id != null && this.id.equals(id);
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

    protected void setEventCallback(String name, IEvent event) {
        if (events == null) {
            events = new HashMap<>();
        }

        events.put(name, event);
    }

    protected void setEventCallback(IEvent.PresetIdentifier identifier, IEvent event) {
        setEventCallback(identifier.name(), event);
    }

    public void setTag(Object key, Object value) {
        if (tags == null) {
            tags = new HashMap<>();
        }

        tags.put(key, value);
    }

    public Object getTag(Object key, Object defaultValue) {
        if (tags == null || tags.isEmpty()) {
            return null;
        }

        return tags.getOrDefault(key, defaultValue);
    }

    public IEvent getEventByName(String name) {
        if (events == null || events.isEmpty()) {
            return null;
        }

        return events.getOrDefault(name, null);
    }

    public Object executeEvent(String event, Control sender, EventArguments arguments) {
        IEvent action = getEventByName(event);
        if (action == null) {
            return null;
        }

        return action.invoke(sender, arguments);
    }

    public Object executeEvent(IEvent.PresetIdentifier event, Control sender, EventArguments arguments) {
        return executeEvent(event.name(), sender, arguments);
    }

    public boolean isAvailable() {
        return loaded && !excluded;
    }

    public boolean isVisible() {
        return visible && opacity > 0.0f;
    }

    protected boolean executeEventChain(IEvent.PresetIdentifier event, EventArguments arguments, Predicate<Object> checker, int depth) {
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

    protected boolean executeEventChain(IEvent.PresetIdentifier event, EventArguments arguments, Predicate<Object> checker, boolean executeChilds) {
        return executeEventChain(event, arguments, checker, executeChilds ? -1 : 0);
    }

    private static boolean nonNull(Object object) {
        return object != null;
    }

    public boolean load() {
        return loaded = executeEventChain(IEvent.PresetIdentifier.LOAD, new EventArguments(), Control::nonNull, true);
    }

    public boolean unload() {
        return loaded = !executeEventChain(IEvent.PresetIdentifier.UNLOAD, new EventArguments(), Control::nonNull, true);
    }

    public boolean update(boolean updateChilds) {
        return executeEventChain(IEvent.PresetIdentifier.UPDATE, new EventArguments(), Control::nonNull, updateChilds);
    }

    public boolean render(RenderProvider provider) {
        if (!isAvailable() || !isVisible()) {
            return false;
        }

        RenderEventArguments context = new RenderEventArguments(provider);

        return executeEventChain(IEvent.PresetIdentifier.RENDER, context, Control::nonNull, true);
    }
}
