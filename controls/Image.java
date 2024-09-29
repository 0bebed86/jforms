package jgui.controls;

import Control;

public class Image extends Control {
    protected String texture;

    public Image(String id, String texture, int x, int y, int z, int width, int height){
        super(id, x, y, width, height);
        super.z = z;

        this.texture = texture;
    }
}
