package jforms.example;

import jforms.Context;
import jforms.event.*;
import jforms.example.forms.*;
import jforms.render.*;

public class Application {

    public static void main(String[] args) {
        Context jformsContext = new Context(null); //well you can create it by yourself how you need

        //any font initialization | now it just gets it from context render provider
        Font currentFont = jformsContext.getRenderProvider().getFont();

        //create form instance
        SampleForm sampleForm = new SampleForm(null, 0, 0, 600, 400, currentFont);

        //register form with all childs as root
        jformsContext.registerElement(sampleForm, true, true);

        //sample example your render loop or something idk :P
        boolean active = false; //ok imagine it is true or a complex condition idgf :\
        while (active) {
            //render all visible controls
            jformsContext.render();
        }

        //sample your event validation loop
        while (active) {
            //validate and execute happens events
            jformsContext.validate(EventPreset.MOUSE_CLICK, Application::mouseClickValidator, true);
        }
    }

    protected static EventArguments mouseClickValidator(Control control, String eventName) {
        //here you implements your check of mouse click on control

        return null;
    }
}
