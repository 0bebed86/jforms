package jgui.controls;

import java.util.ArrayList;
import java.util.List;

public class Button extends Image {

    protected PlainText text;
    protected List<Icon> icons;

    public Button(String id, int x, int y, int width, int height, String texture, PlainText text, List<Icon> icons) {
        super(id, texture, x, y, 0, width, height);

        if (text != null || icons != null) {
            super.childs = new ArrayList<>();

            if (text != null) {
                super.childs.add(text);
            }

            if (icons != null) {
                super.childs.addAll(icons);
            }
        }
        
        this.text = text;
        this.icons = icons;
    }

    /*public static void example(){
        FontInfo defaultFont = new FontInfo(); //for example
        PlainText buttonLabel = new PlainText("button_clickme_label", "Click me!", 5, 5, -1, -1, defaultFont);
        Button button = new Button("button_clickme", 150, 150, 150, 50, null, buttonLabel, null);

        button.setEvent(IEvent.PresetIdentifier.MOUSE_CLICK, (s, e) -> {
            Button element = (Button)s; //equals mymegacockbluster
            MouseEvent event = (MouseEvent)e;

            return null;
        });
    }*/
}
