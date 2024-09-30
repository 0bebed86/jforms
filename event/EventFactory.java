package jgui.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import jgui.render.Control;

public class EventFactory extends HashMap<String, List<? extends Control>>{
    public void registerControl(Control control, boolean registerChilds) {
        Queue<? extends Control> coming = new LinkedList<>();

        while (!coming.isEmpty()) {
            Control current = coming.remove();

            for (Map.Entry<String, IEventCallback> entry : current.getEventCallbacks().entrySet()) {
                String event = entry.getKey();
                List<? extends Control> elements = super.getOrDefault(event, null);
                if (elements == null) {
                    super.put(event, elements = new ArrayList<>());
                }

                if (!elements.contains(current)) {
                    ((List)elements).add(current);
                }
            }

            if(registerChilds){
                current.getChilds().forEach(c -> ((List)coming).add(c));
            }
        }
    }

    public void executeEventsChains(String event, EventArguments arguments){
        List<? extends Control> elements = super.getOrDefault(event, null);
        if (elements == null) {
            return;
        }

        for(Control element : elements){
            element.executeEventChain(event, arguments, true);
        }
    }

    public void executeEventsChains(EventPreset event, EventArguments arguments){
        executeEventsChains(event.name(), arguments);
    }
}
