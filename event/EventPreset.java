package jforms.event;

public enum EventPreset {
    LOAD, UNLOAD,
    PRE_RENDER, RENDER, POST_RENDER,
    UPDATE,
    EVENT_VALIDATE,
    MOUSE_CLICK, MOUSE_DOWN, MOUSE_UP, MOUSE_ENTER, MOUSE_LEAVE, MOUSE_WHEEL, MOUSE_MOVED,
    KEY_CLICK, KEY_DOWN, KEY_UP,
    BEGIN_RESIZE, END_RESIZE, BEGIN_MOVE, END_MOVE,
    DATA_CHANGED;
}
