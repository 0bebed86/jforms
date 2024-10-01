package jgui.event;

import jgui.render.Control;

public interface IEventValidator {
    public EventArguments validate(Control control, String event);
}
