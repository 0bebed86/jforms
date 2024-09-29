package jgui;

import java.util.ArrayList;

public enum MouseCursor {
    ARROW("cursor_arrow"),
    DEFAULT(ARROW.texture),
    POINTER("cursor_pointer");

    public static interface ILoader {
        boolean load(MouseCursor cursor);
    }

    protected String texture;
    protected boolean loaded;

    private MouseCursor(String texture) {
        this.texture = texture;
    }

    public boolean load(ILoader loader, boolean force) {
        if (loaded && !force) {
            return false;
        }

        return loaded = loader.load(this);
    }

    public static MouseCursor[] loadAll(ILoader loader, boolean force) {
        ArrayList<MouseCursor> failed = new ArrayList<>();
        MouseCursor[] coming = MouseCursor.values();

        for (MouseCursor cursor : coming) {
            if (!cursor.load(loader, force)) {
                failed.add(cursor);
            }
        }

        return failed.isEmpty() ? null : (MouseCursor[]) failed.toArray();
    }
}
