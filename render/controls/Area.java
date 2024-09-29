package jgui.render.controls;

import jgui.event.IEvent;
import jgui.render.ColorRGBA;
import jgui.render.ShapeType;

public class Area extends Canvas{
    protected Slider<Integer> verticalScroll, horizontalScroll;

    protected int virtualWidth, virtualHeight;
    protected int virtualX, virtualY;

    public Area(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEvent renderEvent, boolean verticalScroll, boolean horizontalScroll) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);

        if(verticalScroll){
            //this.verticalScroll = new Slider<Integer>(); <- todo something like that | create scrolls

            //addChild(this.verticalScroll);
        }

        if(horizontalScroll){

        }

        this.virtualWidth = width;
        this.virtualHeight = height;

        this.virtualX = this.virtualY = 0;
    }
}
