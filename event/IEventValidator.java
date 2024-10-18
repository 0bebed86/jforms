package jforms.event;

import jforms.render.Control;

public interface IEventValidator {

    public EventArguments validate(Control control, String event);
}
