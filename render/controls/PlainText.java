package jforms.render.controls;

import jforms.event.EventArguments;
import jforms.event.EventPreset;
import jforms.event.IEventCallback;
import jforms.event.arguments.RenderEventArguments;
import jforms.render.ColorRGBA;
import jforms.render.Control;
import jforms.render.Font;
import jforms.render.RenderProvider;
import jforms.render.ShapeType;

public class PlainText extends Canvas {

    protected String value;
    protected Font font;

    protected ColorRGBA color;
    protected int paddingX, paddingY;

    public PlainText(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEventCallback renderEvent, String text, Font font, ColorRGBA color, int paddingX, int paddingY) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);

        this.value = value;
        this.font = font;
        this.color = color;

        if (width == -1) {
            width = font.getTextWidth(text);
        }

        if (height == -1) {
            height = font.getTextHeight(text);
        }

        this.width = width;
        this.height = height;

        this.paddingX = paddingX;
        this.paddingY = paddingY;

        setEventCallback(EventPreset.RENDER, Shape::renderEvent);
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected static boolean renderEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Shape) || !(arguments instanceof RenderEventArguments)) {
            return false;
        }

        PlainText element = (PlainText) control;
        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        Canvas.renderEvent(control, arguments);

        if (element.value != null && !element.value.isEmpty()) {
            provider.renderString(element.value, element.x + element.paddingX, element.y + element.paddingY, element.z, element.width, element.height, element.color.getValue());
        }

        return true;
    }
}
