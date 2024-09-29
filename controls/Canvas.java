package jgui.controls;

import jgui.ColorRGBA;
import jgui.Control;
import jgui.EventArguments;
import jgui.IEvent;
import jgui.RenderProvider;
import jgui.events.arguments.RenderEventArguments;

public class Canvas extends Image {
    protected IEvent renderEvent;

    //todo: scrolls

    public Canvas(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, RenderProvider.ShapeType type, String texture, IEvent renderEvent) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture);

        this.renderEvent = renderEvent;

        setEventCallback(IEvent.PresetIdentifier.RENDER, Canvas::renderEvent);
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
