package jforms.render.controls;

import jforms.event.IEventCallback;
import jforms.render.ColorRGBA;
import jforms.render.ShapeType;

public abstract class Form extends Canvas {

    public Form(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, String texture, IEventCallback renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, ShapeType.QUAD, texture, renderEvent);
    }

    public Form(String id, int x, int y, int z, int width, int height, IEventCallback renderEvent) {
        this(id, x, y, z, width, height, null, null, null, renderEvent);
    }
}
