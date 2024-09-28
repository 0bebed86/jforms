package jgui.controls;

import jgui.Control;
import jgui.EventArguments;
import jgui.IEvent;

public class Canvas extends Image {
    //todo: scrolls

    public Canvas(String id, String texture, int x, int y, int z, int width, int height) {
        super(id, texture, x, y, z, width, height);

        super.setEvent(IEvent.PresetIdentifier.RENDER, Canvas::defaultRenderEvent);
    }

    protected static boolean defaultRenderEvent(Object sender, EventArguments arguments) {
        Canvas element = (Canvas) sender;

        Control.getRenderer().renderTexture(element.texture, element.x, element.y, element.z, element.width, element.height);

        //same with:
        //getRenderer().renderTexture(element.texture, element.x, element.y, element.z, element.width, element.height);
        //RenderProvider.get().renderTexture(element.texture, element.x, element.y, element.z, element.width, element.height);
        return true;
    }
}
