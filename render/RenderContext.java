package jgui.render;

import java.util.Collection;
import jgui.event.EventFactory;

public class RenderContext {
    protected RenderProvider renderer;
    protected EventFactory eventer;
    
    protected Control root;
    protected Collection<? extends Control> elements;
}
