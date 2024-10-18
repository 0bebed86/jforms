package jforms.render.controls;

import jforms.event.EventArguments;
import jforms.event.EventPreset;
import jforms.event.arguments.RenderEventArguments;
import jforms.render.ColorRGBA;
import jforms.render.Control;
import jforms.render.RenderProvider;
import jforms.render.ShapeType;

public class Shape extends Control {

    protected ColorRGBA borderColor;
    protected ColorRGBA bodyColor;
    protected ShapeType type;
    protected float rounding;

    public Shape(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, float rounding) {
        super(id, x, y, width, height);
        super.z = z;

        this.borderColor = borderColor;
        this.bodyColor = bodyColor;
        this.type = type;
        this.rounding = rounding;

        setEventCallback(EventPreset.RENDER, Shape::renderEvent);
    }

    public Shape(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type) {
        this(id, x, y, z, width, height, borderColor, bodyColor, type, 0.0f);
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
