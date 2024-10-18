package jforms.render.controls;

import jforms.event.EventArguments;
import jforms.event.EventPreset;
import jforms.event.IEventCallback;
import jforms.event.arguments.RenderEventArguments;
import jforms.render.ColorRGBA;
import jforms.render.Control;
import jforms.render.RenderProvider;
import jforms.render.ShapeType;

public class Canvas extends Image {

    protected IEventCallback renderEvent;

    public Canvas(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEventCallback renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture);

        this.renderEvent = renderEvent;

        setEventCallback(EventPreset.RENDER, Canvas::renderEvent);
    }

    protected static boolean renderEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Canvas) || !(arguments instanceof RenderEventArguments)) {
            return false;
        }

        Image.renderEvent(control, arguments);

        Canvas element = (Canvas) control;
        RenderEventArguments context = (RenderEventArguments) arguments;
        RenderProvider provider = context.getProvider();

        if (element.renderEvent != null) {
            element.renderEvent.invoke(control, arguments);
        }

        return true;
    }
}
