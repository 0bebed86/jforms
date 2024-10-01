package jgui.render;

public class ViewPort {
    protected int left, top, right, bottom, width, height;

    public ViewPort(){
        return;
    }

    protected ViewPort(int left, int top, int right, int bottom, int width, int height){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.width = width;
        this.height = height;
    }

    public ViewPort(int left, int top, int right, int bottom){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.width = right - left;
        this.height = bottom - top;
    }

    public ViewPort(ViewPort source){
        this(source.left, source.top, source.right, source.bottom, source.width, source.height);
    }

    public int getLeft(){
        return left;
    }

    public int getRight(){
        return right;
    }

    public int getTop(){
        return top;
    }

    public int getBottom(){
        return bottom;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getX(){
        return left;
    }

    public int getY(){
        return top;
    }

    public ViewPort merge(ViewPort another, boolean clone){
        ViewPort element = clone ? new ViewPort(this) : this;

        element.left += another.left;
        element.top += another.top;
        element.right += another.right;
        element.bottom += another.bottom;

        element.width = element.right - element.left;
        element.height = element.bottom - element.top;

        return element;
    }
}
