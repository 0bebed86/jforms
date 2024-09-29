package jgui.event;

import java.time.Instant;

public class EventArguments {
    protected long time;

    public EventArguments(long time){
        this.time = time;
    }

    public EventArguments(){
        this(Instant.now().toEpochMilli());
    }
}
