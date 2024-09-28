package jgui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Control {

    protected String id = null;

    //protected Cursor cursor = Cursor.DEFAULT; //todo idk how and idc dgf

    //protected Control owner = null;
    //protected Control parent = null;
    protected List<Control> childs = null;
    protected Map<Object, Object> tags = null;
    protected Map<String, IEvent> events = null;

    protected int x = 0; //display x
    protected int y = 0; //display y
    protected int z = 0; //layer index

    protected int width = 0;
    protected int height = 0;
    protected int depth = 0;

    protected boolean visible = true;
    protected boolean active = false;
    protected boolean enabled = true;

    protected float opacity = 1.0f;

    protected static RenderProvider getRenderer() {
        return RenderProvider.get();
    }

    protected Control(String id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean swapVisible(){
        return visible = !visible;
    }

    public boolean swapActive(){
        return active = !active;
    }

    public boolean swapEnabled(){
        return enabled = !enabled;
    }

    protected void setEvent(String name, IEvent event) {
        if (events == null) {
            events = new HashMap<>();
        }

        events.put(name, event);
    }

    protected void setEvent(IEvent.PresetIdentifier identifier, IEvent event) {
        setEvent(identifier.name(), event);
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

    protected boolean idEquals(String id) {
        return this.id != null && this.id.equals(id);
    }

    public Control getChildById(String id, int depth) {
        for (Control control : childs) {
            if (control.idEquals(id)) {
                return control;
            }

            if (depth > 0 || depth == -1) {
                Control result = control.getChildById(id, depth == -1 ? depth : depth - 1);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
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

    public boolean isVisible() {
        return visible && opacity > 0.0f;
    }

    public void load() {
        return; //todo
    }

    public void update() {
        return; //todo
    }

    public boolean render() {
        if (!isVisible()) {
            return false;
        }

        if (executeEvent(IEvent.PresetIdentifier.RENDER, this, new EventArguments()) == null) {
            return false;
        }

        if (childs != null) {
            for (Control child : childs) {
                child.render();
            }
        }

        return true;
    }

    /*protected int adopt(Control child, boolean force){
        if(childs == null){
            childs = new ArrayList<>();
        }

        if(child.link(this, force)){
            childs.add(child);
        }

        return childs.size();
    }

    protected boolean link(Control parent, boolean force){
        if(this.parent != null && !force){
            return false;
        }

        this.parent = parent;
        this.owner = parent.owner;

        return true;
    }*/
}
