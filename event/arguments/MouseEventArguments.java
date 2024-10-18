package jforms.event.arguments;

import jforms.MouseButton;
import jforms.event.EventArguments;

public class MouseEventArguments extends EventArguments {

    public int x, y; //position
    public int count; //clicks
    public int factor; //wheel
    public MouseButton button; //l/r/m/x

    public MouseEventArguments(String event, int x, int y, int count, int factor, MouseButton button) {
        super(event);

        this.x = x;
        this.y = y;
        this.count = count;
        this.factor = factor;
        this.button = button;
    }
}
