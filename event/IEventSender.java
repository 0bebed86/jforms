package jgui.event;

import java.util.Collection;
import jgui.render.Control;

public interface IEventSender {

    public Object send(String event, Collection<? extends Control> registered);
}
