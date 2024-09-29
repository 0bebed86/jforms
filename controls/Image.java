package jgui.controls;

import jgui.ColorRGBA;
import jgui.Control;
import jgui.EventArguments;
import jgui.IEvent;
import jgui.RenderProvider;
import jgui.events.arguments.RenderEventArguments;

public class Image extends Shape {

    protected String texture;

    public Image(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, RenderProvider.ShapeType type, String texture) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type);

        this.texture = texture;

        setEventCallback(IEvent.PresetIdentifier.RENDER, Image::renderEvent);
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
