package jgui.events;

import EventArguments;

public class MouseEvent extends EventArguments {
    public int x, y; //position
    public int count; //clicks
    public int button; //l/r/m/x

    public MouseEvent(){
        super();
    }
}
