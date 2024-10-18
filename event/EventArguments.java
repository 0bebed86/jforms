package jforms.event;

import java.time.Instant;

public class EventArguments {

    protected String event;
    protected long time;
    protected boolean abort;

    public EventArguments(String event, long time) {
        this.time = time;
    }

    public EventArguments(EventPreset event, long time) {
        this(event.name(), time);
    }

    public EventArguments(String event) {
        this(event, Instant.now().toEpochMilli());
    }

    public EventArguments(EventPreset event) {
        this(event.name());
    }

    public String getEvent() {
        return event;
    }

    public long getTime() {
        return time;
    }

    public boolean isAbort() {
        return abort;
    }

    public void initiateAbort() {
        abort = true;
    }
}
