package jforms.event.arguments;

import jforms.event.EventArguments;

public class ValidationEventArguments extends EventArguments {

    protected String event;

    public ValidationEventArguments(String event) {
        super();

        this.event = event;
    }
}
