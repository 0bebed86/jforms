package jforms.render.controls;

import jforms.render.ColorRGBA;
import jforms.render.ShapeType;
import jforms.event.IEventCallback;

public class Icon extends Canvas {

    public Icon(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEventCallback renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);
    }
}
