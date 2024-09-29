package jgui.controls;

import jgui.ColorRGBA;
import jgui.IEvent;
import jgui.RenderProvider;

public class Icon extends Canvas {
    public Icon(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, RenderProvider.ShapeType type, String texture, IEvent renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);
    }
}
