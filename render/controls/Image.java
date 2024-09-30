package jgui.render.controls;

import jgui.event.EventArguments;
import jgui.event.EventPreset;
import jgui.event.arguments.RenderEventArguments;
import jgui.render.ColorRGBA;
import jgui.render.Control;
import jgui.render.RenderProvider;
import jgui.render.ShapeType;

public class Image extends Shape {

    protected String texture;

    public Image(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type);

        this.texture = texture;

        setEventCallback(EventPreset.RENDER, Image::renderEvent);
    }

    protected static boolean renderEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Image) || !(arguments instanceof RenderEventArguments)) {
            return false;
        }

        Shape.renderEvent(control, arguments);

        Image element = (Image) control;
        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        if (element.texture != null) {
            provider.renderTexture(element.texture, element.x, element.y, element.z, element.width, element.height);
        }

        return true;
    }
}
