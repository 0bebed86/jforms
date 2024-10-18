package jforms.event.arguments;

import jforms.event.EventArguments;
import jforms.render.Control;
import jforms.render.RenderProvider;

public class RenderEventArguments extends EventArguments {

    protected Control rootElement;
    protected RenderProvider renderProvider;

    public RenderEventArguments(Control root, RenderProvider renderProvider) {
        super();

        this.renderProvider = renderProvider;
    }

    public RenderProvider getProvider() {
        return renderProvider;
    }
}
