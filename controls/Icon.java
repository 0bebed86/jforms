package jgui.controls;

import jgui.render.ColorRGBA;
import jgui.event.IEvent;
import jgui.render.RenderProvider.ShapeType;

public class Icon extends Canvas {
    public Icon(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEvent renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);
    }
}
