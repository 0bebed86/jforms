package jgui;

public interface IEvent {
    public static enum PresetIdentifier {
        LOAD, RENDER, UPDATE,
        MOUSE_CLICK, MOUSE_DOWN, MOUSE_UP, MOUSE_ENTER, MOUSE_LEAVE, MOUSE_WHEEL, MOUSE_MOVED,
        KEY_CLICK, KEY_DOWN, KEY_UP,
        BEGIN_RESIZE, END_RESIZE, BEGIN_MOVE, END_MOVE;
    }

    public Object invoke(Control sender, EventArguments arguments);
}
