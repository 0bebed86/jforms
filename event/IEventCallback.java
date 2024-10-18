package jforms.event;

import jforms.render.Control;

public interface IEventCallback {

    public Object invoke(Control sender, EventArguments arguments);
}
