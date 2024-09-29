package jgui;

public enum MouseButton {
    INVALID(-1), LEFT(0), MIDDLE(1), RIGHT(2), X(3);

    protected int value;

    private MouseButton(int value){
        this.value = value;
    }

    public boolean isInvalid(){
        return this == INVALID;
    }

    public boolean isLeft(){
        return this == LEFT;
    }

    public boolean isMiddle(){
        return this == MIDDLE;
    }

    public boolean isRight(){
        return this == RIGHT;
    }

    public boolean isX(){
        return this == X;
    }
}
