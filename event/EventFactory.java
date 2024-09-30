package jgui.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import jgui.render.Control;

public abstract class EventFactory {

    protected static class Node {

        public Queue<IEventSender> processors;
        public List<? extends Control> elements;

        public Node() {
            this.processors = new LinkedList<>();
            this.elements = new ArrayList<>();
        }

        public boolean addElement(Control element) {
            if (elements.contains(element)) {
                return false;
            }

            return ((List) elements).add(element);
        }

        public int addProcessor(IEventSender processor) {
            int index = processors.size();

            processors.add(processor);

            return index;
        }
    }

    protected static final Map<String, Node> registry = new HashMap<>();

    public void registerControl(Control control, boolean registerChilds) {
        Queue<? extends Control> coming = new LinkedList<>();

        while (!coming.isEmpty()) {
            Control current = coming.remove();

            for (Map.Entry<String, IEventCallback> entry : current.getEventCallbacks().entrySet()) {
                String event = entry.getKey();
                Node node = registry.getOrDefault(event, null);
                if (node == null) {
                    registry.put(event, new Node());
                }

                node.addElement(current);
            }

            if(registerChilds){
                current.getChilds().forEach(c -> ((List)coming).add(c));
            }
        }
    }

    public void executeEvents(String event, EventArguments arguments){
        Node node = registry.getOrDefault(event, null);
        if (node == null) {
            return;
        }

        for(Control element : node.elements){
            element.executeEventChain(event, arguments, true);
        }
    }
}
