package jgui.controls;

import jgui.FontInfo;
import jgui.event.IEvent;
import jgui.render.ColorRGBA;
import jgui.render.RenderProvider.ShapeType;

public class PlainText extends Canvas {
    protected String value;
    protected FontInfo font;
    protected ColorRGBA color;

    public PlainText(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEvent renderEvent, String text, FontInfo font, ColorRGBA color) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);

        this.value = value;
        this.font = font;
        this.color = color;

        if(width == -1){
            //todo: calculateWidth
        }

        if(height == -1){
            //todo: calculateHeight
        }
    }

    public void setValue(String value){
        this.value = value;
    }
}
