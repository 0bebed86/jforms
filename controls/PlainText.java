package jgui.controls;

import Control;
import FontInfo;

public class PlainText extends Control {
    protected String label;
    protected FontInfo font;

    public PlainText(String id, String label, int x, int y, int width, int height, FontInfo font) {
        super(id, x, y, width, height);

        this.label = label;
        this.font = font;

        if(width == -1){
            //todo: calculateWidth
        }

        if(height == -1){
            //todo: calculateHeight
        }
    }
}
