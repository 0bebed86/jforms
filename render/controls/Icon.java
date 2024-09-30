package jgui.render.controls;

import jgui.render.ColorRGBA;
import jgui.render.ShapeType;
import jgui.event.IEventCallback;

public class Icon extends Canvas {

    public Icon(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEventCallback renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);
    }
}
