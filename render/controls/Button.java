package jforms.render.controls;

import java.util.List;
import jforms.event.IEventCallback;
import jforms.render.ColorRGBA;
import jforms.render.Font;
import jforms.render.ShapeType;

public class Button extends PlainText {

    protected List<Icon> icons;

    public Button(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor,
            ShapeType type, String texture, IEventCallback renderEvent, String text, Font font, ColorRGBA color,
            List<Icon> icons) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent, text, font, color);

        if (icons != null) {
            super.addChilds(icons);
        }

        this.icons = icons;
    }
}
