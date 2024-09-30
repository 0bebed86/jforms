package jgui.event;

import jgui.render.Control;

public interface IEventCallback {
    public Object invoke(Control sender, EventArguments arguments);
}
