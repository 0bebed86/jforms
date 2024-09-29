package jgui.event.arguments;

import jgui.event.EventArguments;

public class MouseEventArguments extends EventArguments {
    public int x, y; //position
    public int count; //clicks
    public int button; //l/r/m/x

    public MouseEventArguments(int x, int y, int button) {
        super();

        this.x = x;
        this.y =y;
        this.count = 1;
        this.button = button;
    }
}
