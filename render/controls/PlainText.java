package jforms.render.controls;

import jforms.render.ColorRGBA;
import jforms.render.Font;
import jforms.render.ShapeType;
import jforms.event.IEventCallback;

public class PlainText extends Canvas {

    protected String value;
    protected Font font;
    protected ColorRGBA color;

    public PlainText(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEventCallback renderEvent, String text, Font font, ColorRGBA color) {
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
