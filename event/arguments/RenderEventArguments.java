package jgui.event.arguments;

import jgui.event.EventArguments;
import jgui.render.RenderProvider;

public class RenderEventArguments extends EventArguments {

    protected RenderProvider renderer;

    public RenderEventArguments(RenderProvider renderer) {
        super();

        this.renderer = renderer;
    }

    public RenderProvider getProvider(){
        return renderer;
    }
}
