package jgui.example;

import jgui.Context;
import jgui.example.forms.*;
import jgui.event.*;
import jgui.event.arguments.*;
import jgui.render.*;
import jgui.render.controls.*;

public class Application {
    public static void main(String[] args) {
        Context jguiContext = new Context(null); //well you can create it by yourself how you need

        //any font initialization | now it just gets it from context render provider
        Font currentFont = jguiContext.getRenderProvider().getFont();

        //create form instance
        SampleForm sampleForm = new SampleForm(null, 0, 0, 600, 400, currentFont);

        //register form with all childs as root
        jguiContext.registerElement(sampleForm, true, true);
        
        //sample example your render loop or something idk :P
        boolean active = false; //ok imagine it is true or a complex condition idgf :\
        while(active){
            jguiContext.render();
        }

        //sample your event validation loop
        while(active){
            jguiContext.validate(EventPreset.MOUSE_CLICK, Application::mouseClickValidator, true);
        }
    }

    protected static EventArguments mouseClickValidator(Control control, String eventName){
        //here you implements your check of mouse click on control

        return null;
    }
}
