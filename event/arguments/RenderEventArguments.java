package jgui.event.arguments;

import jgui.render.RenderContext;
import jgui.event.EventArguments;
import jgui.render.RenderProvider;

public class RenderEventArguments extends EventArguments {

    protected RenderContext context;

    public RenderEventArguments(RenderContext context) {
        super();

        this.context = context;
    }

    public RenderProvider getProvider(){
        return context.getRenderProvider();
    }
}
