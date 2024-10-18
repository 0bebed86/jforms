package jforms.event;

import java.time.Instant;

public class EventArguments {

    protected long time;
    protected boolean abort;

    public EventArguments(long time) {
        this.time = time;
    }

    public EventArguments() {
        this(Instant.now().toEpochMilli());
    }

    public long getTime() {
        return time;
    }

    public boolean isAbort() {
        return abort;
    }
}
