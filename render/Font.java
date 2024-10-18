package jforms.render;

import jforms.util.vector.Vector2T;

public abstract class Font {

    protected String filePath;

    public Font(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public abstract int getTextWidth(String text);

    public abstract int getTextHeight(String text);

    public abstract Vector2T<Integer> getTextSize(String text);
}
