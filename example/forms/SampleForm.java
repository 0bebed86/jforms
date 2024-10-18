package jforms.example.forms;

import jforms.event.*;
import jforms.event.arguments.*;
import jforms.render.*;
import jforms.render.controls.*;

//create form class extends jforms.render.controls.Form
public class SampleForm extends Form {

    //define needed constants
    protected static final ColorRGBA borderColor = null;
    protected static final ColorRGBA backgroundColor = new ColorRGBA(java.awt.Color.darkGray);
    protected static final String backgroundTexture = null;
    protected static final ColorRGBA textColor = new ColorRGBA(java.awt.Color.white);

    //define needed variables
    protected Font font = null;

    //define controls that be owned by this form
    protected Button clickerButton = null;
    protected PlainText counterLabel = null;

    //create cunstructor matching super
    public SampleForm(String id, int x, int y, int width, int height, Font font) {
        super(id, x, y, 0, width, height, borderColor, backgroundColor, backgroundTexture, null); //init Form 

        initializeElements(font); //call elements initializer
    }

    //create controls initialization method (or init they immidiately in constructor)
    protected void initializeElements(Font font) {
        this.font = font;

        //initialize all controls
        counterLabel = new PlainText(null, 150 + 150 + 5, 50, 0, -1, -1, null, null, ShapeType.QUAD, null, null, "Unclicked yet", font, textColor);
        {
            addChild(counterLabel);
        }

        clickerButton = new Button(null, 150, 150, 0, 150, 50, null, backgroundColor, ShapeType.QUAD, null, null, "Click me left!", font, textColor, null);
        {
            //set button tag "preview" for link with label
            clickerButton.setTag("preview", counterLabel);

            //set button MOUSE_CLICK event callback
            clickerButton.setEventCallback(EventPreset.MOUSE_CLICK, SampleForm::clickerButtonMouseClickEvent); //when callback is static
            //clickerButton.setEventCallback(EventPreset.MOUSE_CLICK, this::clickerButtonMouseClickEvent); //when callback is dynamic

            addChild(clickerButton);
        }
    }

    //implement events callbacks | preferably they should be static, so that they can be called from other forms if sender have neighbor-behavior
    protected static Object clickerButtonMouseClickEvent(Control sender, EventArguments arguments) {
        //take event arguments as mouse event arguments
        MouseEventArguments context = (MouseEventArguments) arguments;
        if (!context.button.isLeft()) {
            return null; //break if clicked button isnt left
        }

        Button element = (Button) sender;
        if (element == null) {
            return null; //break if sender invalid
        }

        PlainText label = element.getTag("preview", null, false);
        if (label == null) {
            return null; //break if element havent "preview" tag (invalid sender btw)
        }

        //get or create "value" tag in preview element
        Integer count = label.getTag("value", 0, true);

        //increment value and convert to string
        String stringed = (count++).toString();

        //set label text
        label.setValue("Clicked left " + stringed + " times");

        //trigger update events, or not if you dont want it
        label.update(true);

        //return something, for example boolean true
        return true;
    }
}
