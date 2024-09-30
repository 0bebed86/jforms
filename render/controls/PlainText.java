package jgui.render.controls;

import jgui.render.ColorRGBA;
import jgui.render.FontInfo;
import jgui.render.ShapeType;
import jgui.event.IEventCallback;

public class PlainText extends Canvas {

    protected String value;
    protected FontInfo font;
    protected ColorRGBA color;

    public PlainText(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEventCallback renderEvent, String text, FontInfo font, ColorRGBA color) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);

        this.value = value;
        this.font = font;
        this.color = color;

        if (width == -1) {
            //todo: calculateWidth
        }

        if (height == -1) {
            //todo: calculateHeight
        }
    }

    public void setValue(String value) {
        this.value = value;
    }
}
