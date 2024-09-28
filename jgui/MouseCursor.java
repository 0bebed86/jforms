package jgui;

public enum MouseCursor {
    ARROW("cursor_arrow"), 
    DEFAULT(ARROW.texture), 
    POINTER("cursor_pointer");

    protected String texture;
    protected boolean loaded;

    private MouseCursor(String texture){
        this.texture = texture;
    }

    public boolean load(boolean force){
        if(loaded && !force){
            return false;
        }

        //todo: load register etc.

        return loaded = true;
    }
}
