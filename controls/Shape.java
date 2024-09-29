package jgui.controls;

import jgui.ColorRGBA;
import jgui.Control;
import jgui.EventArguments;
import jgui.IEvent;
import jgui.RenderProvider;
import jgui.events.arguments.RenderEventArguments;

public class Shape extends Control {

    protected ColorRGBA borderColor;
    protected ColorRGBA bodyColor;
    protected RenderProvider.ShapeType type;

    public Shape(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, RenderProvider.ShapeType type) {
        super(id, x, y, width, height);
        super.z = z;

        this.borderColor = borderColor;
        this.bodyColor = bodyColor;
        this.type = type;

        setEventCallback(IEvent.PresetIdentifier.RENDER, Shape::renderEvent);
    }

    protected static boolean renderEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Shape) || !(arguments instanceof RenderEventArguments)) {
            return false;
        }

        Shape element = (Shape) control;
        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        provider.pushRotation(element.rotation);
        provider.pushShapeType(element.type);

        if (element.bodyColor != null) {
            provider.renderShapeFilled(element.x, element.y, element.z, element.width, element.height, element.bodyColor.getValue());
        }

        if (element.borderColor != null) {
            provider.renderShapeBorder(element.x, element.y, element.z, element.width, element.height, element.borderColor.getValue());
        }

        return true;
    }

}
