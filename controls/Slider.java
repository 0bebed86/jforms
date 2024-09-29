package jgui.controls;

import jgui.ColorRGBA;
import jgui.Control;
import jgui.EventArguments;
import jgui.RenderProvider;
import jgui.IEvent;

public class Slider<T> extends Canvas {
    protected Button handle;

    protected boolean vertical;
    protected T floor, ceil, value;

    public Slider(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, String texture, IEvent renderEvent, Button handle, T floor, T initial, T ceil, boolean vertical) {
        super(id, x, y, z, width, height, borderColor, bodyColor, RenderProvider.ShapeType.QUAD, texture, renderEvent);

        if (handle != null) {
            addChild(handle);
        }

        this.handle = handle;
        this.vertical = vertical;
        this.floor = floor;
        this.value = initial;
        this.ceil = ceil;
    }

    protected static boolean updateEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Slider)) {
            return false;
        }

        Slider element = (Slider) control;

        //todo: adjust handle button position

        return true;
    }
}
