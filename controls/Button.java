package jgui.controls;

import java.util.List;
import jgui.FontInfo;
import jgui.event.IEvent;
import jgui.render.ColorRGBA;
import jgui.render.RenderProvider.ShapeType;

public class Button extends PlainText {

    protected List<Icon> icons;

    public Button(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, ShapeType type, String texture, IEvent renderEvent, String text, FontInfo font, ColorRGBA color, List<Icon> icons) {
        super(id, x, y, z, width, height, borderColor, bodyColor, type, texture, renderEvent, text, font, color);

        if (icons != null) {
            super.addChilds(icons);
        }

        this.icons = icons;
    }

    /*public static void example(){ 
        ColorRGBA textColor = new ColorRGBA(java.awt.Color.white);
        ColorRGBA backgroundColor = new ColorRGBA(java.awt.Color.darkGray);
        FontInfo currentFont = new FontInfo("sampleFont.smple");

        //create clicker button control
        Button button = new Button(null, 150, 150, 0, 150, 50, null, backgroundColor, ShapeType.QUAD, null, null, "Click me left!", currentFont, textColor, null);

        //create clicks label control
        PlainText counterLabel = new PlainText(null, 150 + 150 + 5, 50, 0, -1, -1, null, null, ShapeType.QUAD, null, null, "Unclicked yet", currentFont, textColor);

        //store label control instance pointer in button
        button.setTag("preview", counterLabel);

        //set callback to button click event
        button.setEventCallback(IEvent.PresetIdentifier.MOUSE_CLICK, (s, e) -> {
            //take arguments as mouse event arguments
            jgui.event.arguments.MouseEventArguments context = (jgui.event.arguments.MouseEventArguments) e;

            //check if pressed mouse button is left
            if (!context.button.isLeft()) {
                return null;
            }

            //take sender element as button control
            Button element = (Button) s;

            //check it for null
            if (element == null) {
                return null;
            }

            //try get label from button, if can then its our or neighbor-behavior button, else not
            PlainText label = element.getTag("preview", null, false);

            //check it for null
            if (label == null) {
                return null;
            }

            //get or create current clicks count value
            Integer count = label.getTag("value", 0, true);

            //increase it and get as string
            String stringed = (count++).toString();

            //make label text
            label.setValue("Clicked " + stringed + " times");

            //trigger update label control with his childs
            label.update(true);

            return true;
        });
    }*/
}
