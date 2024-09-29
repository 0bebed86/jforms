package jgui.controls;

import java.util.List;
import jgui.ColorRGBA;
import jgui.IEvent;
import jgui.RenderProvider;

public class Button extends Canvas {

    protected PlainText text;
    protected List<Icon> icons;

    public Button(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, RenderProvider.ShapeType type, String texture, IEvent renderEvent, PlainText text, List<Icon> icons) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent);

        if (text != null) {
            super.addChild(text);
        }

        if (icons != null) {
            super.addChilds(icons);
        }

        this.text = text;
        this.icons = icons;
    }

    /*public static void example(){
        FontInfo defaultFont = new FontInfo(); //for example
        PlainText buttonLabel = new PlainText("button_clickme_label", "Click me!", 5, 5, -1, -1, defaultFont);
        Button button = new Button("button_clickme", 150, 150, 150, 50, null, buttonLabel, null);

        button.setEventCallback(IEvent.PresetIdentifier.MOUSE_CLICK, (s, e) -> {
            Button element = (Button)s; //equals mymegacockbluster
            MouseEvent event = (MouseEvent)e;

            return null;
        });
    }*/
}
