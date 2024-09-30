package jgui.render.controls;

import jgui.event.EventArguments;
import jgui.event.arguments.RenderEventArguments;
import jgui.render.ColorRGBA;
import jgui.render.Control;
import jgui.render.RenderProvider;
import jgui.render.ShapeType;
import jgui.event.IEventCallback;

public class Shape extends Control {

    protected ColorRGBA borderColor;
    protected ColorRGBA bodyColor;
    protected ShapeType type;

    public Shape(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type) {
        super(id, x, y, width, height);
        super.z = z;

        this.borderColor = borderColor;
        this.bodyColor = bodyColor;
        this.type = type;

        setEventCallback(IEventCallback.PresetIdentifier.RENDER, Shape::renderEvent);
    }

    protected static boolean renderEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Shape) || !(arguments instanceof RenderEventArguments)) {
            return false;
        }

        Shape element = (Shape) control;
        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        if (element.bodyColor == null && element.borderColor == null) {
            return true;
        }

        provider.pushRotation(element.rotation, true);
        provider.pushShapeType(element.type, true);

        if (element.bodyColor != null) {
            provider.renderShapeFilled(element.x, element.y, element.z, element.width, element.height, element.bodyColor.getValue());
        }

        if (element.borderColor != null) {
            provider.renderShapeBorder(element.x, element.y, element.z, element.width, element.height, element.borderColor.getValue());
        }

        provider.popRotation(true);
        provider.popShapeType(true);

        return true;
    }

}
