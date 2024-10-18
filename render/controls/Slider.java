package jforms.render.controls;

import jforms.event.EventArguments;
import jforms.event.EventPreset;
import jforms.event.IEventCallback;
import jforms.event.arguments.MouseEventArguments;
import jforms.render.ColorRGBA;
import jforms.render.Control;
import jforms.render.ShapeType;
import jforms.util.CastProcessor;
import jforms.util.PositionAlignment;

public class Slider<T> extends Canvas {

    protected Button handle;
    protected boolean vertical;

    protected boolean mouseActing;

    protected CastProcessor<Long, T> castProcessor;
    protected long floor, ceil, value;
    protected float range;

    public Slider(String id, int x, int y, int z, int width, int height, ColorRGBA borderColor, ColorRGBA bodyColor, String texture, IEventCallback renderEvent, T floor, T initial, T ceil, CastProcessor<Long, T> castProcessor, Button handle, boolean vertical) {
        super(id, x, y, z, width, height, borderColor, bodyColor, ShapeType.QUAD, texture, renderEvent);

        if (handle != null) {
            addChild(handle);
        }

        this.handle = handle;
        this.vertical = vertical;

        this.castProcessor = castProcessor;
        this.floor = castProcessor.front(floor);
        this.ceil = castProcessor.front(ceil);
        this.value = castProcessor.front(initial);
        this.range = (float) (this.ceil - this.floor);

        setEventCallback(EventPreset.MOUSE_DOWN, Slider::mouseDownUpEvent);
        setEventCallback(EventPreset.MOUSE_UP, Slider::mouseDownUpEvent);
        setEventCallback(EventPreset.MOUSE_MOVED, Slider::mouseMovedEvent);
        setEventCallback(EventPreset.UPDATE, Slider::updateEvent);
    }

    protected static boolean mouseDownUpEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Slider) || !(arguments instanceof MouseEventArguments)) {
            return false;
        }

        Slider element = (Slider) control;
        MouseEventArguments context = (MouseEventArguments) arguments;
        String event = arguments.getEvent();

        if (!context.button.isLeft() || event == null || event.isEmpty()) {
            return false;
        }

        if (event.equals(EventPreset.MOUSE_DOWN.name())) {
            element.mouseActing = true;
        } else if (event.equals(EventPreset.MOUSE_UP.name())) {
            element.mouseActing = false;
        }

        return true;
    }

    protected static boolean mouseMovedEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Slider) || !(arguments instanceof MouseEventArguments)) {
            return false;
        }

        Slider element = (Slider) control;
        MouseEventArguments context = (MouseEventArguments) arguments;

        if (!context.button.isLeft()) {
            return false;
        }

        element.setValue(context.x, context.y);

        element.executeEvent(EventPreset.DATA_CHANGED, control, arguments);

        element.update(true);

        return true;
    }

    protected static boolean updateEvent(Control control, EventArguments arguments) {
        if (!(control instanceof Slider)) {
            return false;
        }

        Slider element = (Slider) control;

        element.applyHandle();

        return true;
    }

    protected long setValue(int mouseX, int mouseY) {
        int minimal;
        int maximal;
        int position;

        if (this.vertical) {
            minimal = getTop();
            maximal = getBottom();
            position = mouseY;
        } else {
            minimal = getLeft();
            maximal = getRight();
            position = mouseX;
        }

        float factor = PositionAlignment.factor((float) minimal, (float) position, (float) maximal);

        return this.value = this.floor + ((long) (this.range * factor));
    }

    protected void applyHandle() {
        int minimal;
        int maximal;

        if (this.vertical) {
            minimal = getTop();
            maximal = getBottom();
        } else {
            minimal = getLeft();
            maximal = getRight();
        }

        float factor = PositionAlignment.factor((float) this.floor, (float) this.value, (float) this.ceil);
        int position = minimal + ((int) (((float) (maximal - minimal)) * factor));

        if (this.vertical) {
            handle.setY(position);
        } else {
            handle.setX(position);
        }
    }

    public T getValue() {
        return castProcessor.back(this.value);
    }

    public void setValue(T value, boolean update) {
        long current = castProcessor.front(value);

        if (current > this.ceil) {
            current = this.ceil;
        } else if (current < this.floor) {
            current = this.floor;
        }

        this.value = current;

        if (update) {
            this.update(true);
        }
    }
}
