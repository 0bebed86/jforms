package jforms.event.arguments;

import jforms.event.EventArguments;
import jforms.event.EventPreset;

public class ValidationEventArguments extends EventArguments {
    public ValidationEventArguments(String event) {
        super(event);
    }

    public ValidationEventArguments(EventPreset event) {
        super(event);
    }
}
