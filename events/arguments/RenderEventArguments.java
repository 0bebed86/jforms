package jgui.events.arguments;

import jgui.EventArguments;
import jgui.RenderProvider;

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
