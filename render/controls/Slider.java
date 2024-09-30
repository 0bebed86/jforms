package jgui.render.controls;

import jgui.event.EventArguments;
import jgui.event.arguments.MouseEventArguments;
import jgui.render.ColorRGBA;
import jgui.render.Control;
import jgui.render.ShapeType;
import jgui.event.IEventCallback;

public class Slider<T> extends Canvas {

    protected Button handle;

    protected boolean vertical;
    protected T floor, ceil, value;

    public Slider(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, String texture, IEventCallback renderEvent, T floor, T initial, T ceil, boolean vertical) {
        super(id, x, y, z, width, height, borderColor, bodyColor, ShapeType.QUAD, texture, renderEvent);

        if (handle != null) {
            addChild(handle);
        }

        //this.handle = handle; //todo create button
        this.vertical = vertical;
        this.floor = floor;
        this.value = initial;
        this.ceil = ceil;

        setEventCallback(IEventCallback.PresetIdentifier.MOUSE_MOVED, Slider::mouseMovedEvent);
    }

    public T getValue() {
        return value;
    }

    protected void applyHandlePosition(int x, int y) {
        //todo
    }

    protected static boolean mouseMovedEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Slider) || !(arguments instanceof MouseEventArguments)) {
            return false;
        }

        Slider element = (Slider) control;
        MouseEventArguments context = (MouseEventArguments) arguments;

        if (element == null || !context.button.isLeft()) {
            return false;
        }

        element.applyHandlePosition(context.x, context.y);

        element.executeEvent(IEventCallback.PresetIdentifier.DATA_CHANGED, control, arguments);

        return true;
    }
}
