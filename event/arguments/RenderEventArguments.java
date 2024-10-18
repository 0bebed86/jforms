package jforms.event.arguments;

import jforms.event.EventArguments;
import jforms.event.EventPreset;
import jforms.render.Control;
import jforms.render.RenderProvider;

public class RenderEventArguments extends EventArguments {

    protected Control rootElement;
    protected RenderProvider renderProvider;

    public RenderEventArguments(String event, Control root, RenderProvider provider) {
        super(event);

        this.rootElement = root;
        this.renderProvider = provider;
    }

    public RenderEventArguments(EventPreset event, Control root, RenderProvider provider) {
        this(event.name(), root, provider);
    }

    public Control getRoot(){
        return rootElement;
    }

    public RenderProvider getProvider() {
        return renderProvider;
    }
}
