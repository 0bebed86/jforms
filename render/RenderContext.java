package jgui.render;

import java.util.ArrayList;
import java.util.List;
import jgui.event.EventFactory;

public class RenderContext {
    protected RenderProvider renderer;
    protected EventFactory eventer;
    
    protected Control root;
    protected List<? extends Control> elements;

    public RenderContext(RenderProvider renderer, EventFactory eventer, Control root, List<? extends Control> elements){
        this.renderer= renderer;
        this.eventer = eventer;
        this.root = root;
        this.elements = elements == null ? new ArrayList<>() : elements;

        if(!elements.contains(root)){
            ((List)elements).add(0, root);
        }
    }

    public RenderProvider getRenderProvider(){
        return renderer;
    }

    public EventFactory getEventFactory(){
        return eventer;
    }

    public Control getRootElement(){
        return root;
    }

    public List<? extends Control> getElements(){
        return elements;
    }

    public Control getElementById(String id){
        if(id == null || id.isEmpty()){
            return null;
        }

        for(Control control : elements){
            if(control.compareId(id)){
                return control;
            }
        }
        
        return null;
    }
}
