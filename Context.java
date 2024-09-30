package jgui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import jgui.event.EventFactory;
import jgui.render.Control;
import jgui.render.RenderProvider;

public class Context {

    protected RenderProvider renderProvider;
    protected EventFactory eventFactory;

    protected List<? extends Control> elements;
    protected Control rootElement;

    public Context(RenderProvider renderProvider, EventFactory eventFactory) {
        this.renderProvider = renderProvider;
        this.eventFactory = eventFactory;
        this.elements = new ArrayList<>();
        this.rootElement = null;
    }

    public RenderProvider getRenderProvider() {
        return renderProvider;
    }

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void registerElement(Control element, boolean registerChilds, boolean registerAsRoot) {
        Queue<? extends Control> coming = new LinkedList<>();

        while (!coming.isEmpty()) {
            Control current = coming.remove();

            if (!elements.contains(current)) {
                ((List) elements).add(current);
            }

            eventFactory.registerControl(current, false);

            if (registerChilds) {
                current.getChilds().forEach(c -> ((List) coming).add(c));
            }
        }

        if (registerAsRoot) {
            rootElement = element;
        }
    }

    public void registerElement(Control element, boolean registerChilds) {
        registerElement(element, registerChilds, false);
    }

    public void registerElement(Control element) {
        registerElement(element, true, false);
    }

    public List<? extends Control> getElements() {
        return elements;
    }

    public Control getElementById(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        for (Control control : elements) {
            if (control.compareId(id)) {
                return control;
            }
        }

        return null;
    }

    public void setRootElement(Control element) {
        registerElement(element, true, true);
    }

    public Control getRootElement() {
        return rootElement;
    }

    public boolean load() {
        if(rootElement == null){
            return false;
        }

        return rootElement.load();
    }

    public boolean unload() {
        if(rootElement == null){
            return false;
        }

        return rootElement.unload();
    }

    public boolean update() {
        if(rootElement == null){
            return false;
        }

        return rootElement.update(true);
    }

    public boolean render(){
        if(rootElement == null){
            return false;
        }

        return rootElement.render(this);
    }
}
