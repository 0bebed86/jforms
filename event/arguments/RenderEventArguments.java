package jgui.event.arguments;

import jgui.event.EventArguments;
import jgui.render.Control;
import jgui.render.RenderProvider;

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
