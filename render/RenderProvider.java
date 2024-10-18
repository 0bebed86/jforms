package jforms.render;

import jforms.util.IApplier;

public abstract class RenderProvider {

    protected static class Stack<T> extends java.util.Stack<T> {

        public boolean pushApply(IApplier<T> applier, T element, boolean apply) {
            if (element == null) {
                return false;
            }

            super.push(element);

            if (apply && applier != null) {
                applier.apply(element);
            }

            return true;
        }

        public T popApply(IApplier<T> applier, boolean apply) {
            if (super.empty()) {
                return null;
            }

            T element = super.pop();

            if (apply && applier != null) {
                applier.apply(element);
            }

            return element;
        }

        public T get(T defaultValue) {
            if (super.empty()) {
                return defaultValue;
            }

            return super.peek();
        }
    }

    protected Font defaultFont = null;
    protected MouseCursor defaultCursor = null;

    protected final Stack<Font> fontStack = new Stack<>();
    protected final Stack<MouseCursor> cursorStack = new Stack<>();
    protected final Stack<ViewPort> viewPortStack = new Stack<>();
    protected final Stack<Integer> colorStack = new Stack<>();
    protected final Stack<String> textureStack = new Stack<>();
    protected final Stack<Float> rotationStack = new Stack<>();
    protected final Stack<ShapeType> shapeTypeStack = new Stack<>();

    protected RenderProvider(Font defaultFont) {
        this.defaultFont = defaultFont;
        this.defaultCursor = MouseCursor.DEFAULT;
    }

    protected abstract boolean applyFont(Font font);

    public boolean pushFont(Font font, boolean apply) {
        return fontStack.pushApply(this::applyFont, font, apply);
    }

    public Font popFont(boolean apply) {
        return fontStack.popApply(this::applyFont, apply);
    }

    public Font getFont() {
        return fontStack.get(defaultFont);
    }

    protected abstract boolean applyCursor(MouseCursor cursor);

    public boolean pushCursor(MouseCursor cursor, boolean apply) {
        return cursorStack.pushApply(this::applyCursor, cursor, apply);
    }

    public MouseCursor popCursor(boolean apply) {
        return cursorStack.popApply(this::applyCursor, apply);
    }

    public MouseCursor getCursor() {
        return cursorStack.get(MouseCursor.DEFAULT);
    }

    protected abstract boolean applyViewPort(ViewPort viewPort);

    public boolean pushViewPort(ViewPort viewPort, boolean apply) {
        return viewPortStack.pushApply(this::applyViewPort, viewPort, apply);
    }

    public ViewPort popViewPort(boolean apply) {
        return viewPortStack.popApply(this::applyViewPort, apply);
    }

    public ViewPort getViewPort() {
        return viewPortStack.get(null);
    }

    public ViewPort getViewPortAbsolute() {
        if (viewPortStack.isEmpty()) {
            return null;
        }

        ViewPort result = new ViewPort();

        for (ViewPort current : viewPortStack) {
            result.merge(current, false);
        }

        return result;
    }

    protected abstract boolean applyColor(int color);

    public boolean pushColor(int color, boolean apply) {
        return colorStack.pushApply(this::applyColor, color, apply);
    }

    public int popColor(boolean apply) {
        return colorStack.popApply(this::applyColor, apply);
    }

    public int getColor() {
        return colorStack.get(-1);
    }

    protected abstract boolean applyTexture(String id);

    public boolean pushTexture(String id, boolean apply) {
        return textureStack.pushApply(this::applyTexture, id, apply);
    }

    public String popTexture(boolean apply) {
        return textureStack.popApply(this::applyTexture, apply);
    }

    public String getTexture() {
        return textureStack.get(null);
    }

    protected abstract boolean applyRotation(float angle);

    public boolean pushRotation(float angle, boolean apply) {
        return rotationStack.pushApply(this::applyRotation, angle, apply);
    }

    public float popRotation(boolean apply) {
        return rotationStack.popApply(this::applyRotation, apply);
    }

    public float getRotation() {
        return rotationStack.get(0.0f);
    }

    protected abstract boolean applyShapeType(ShapeType type);

    public boolean pushShapeType(ShapeType type, boolean apply) {
        return shapeTypeStack.pushApply(this::applyShapeType, type, apply);

    }

    public ShapeType popShapeType(boolean apply) {
        return shapeTypeStack.popApply(this::applyShapeType, apply);
    }

    public ShapeType getShapeType() {
        return shapeTypeStack.get(null);
    }

    public abstract boolean renderShape(int x, int y, int z, int width, int height, int color, float rounding, boolean fill);

    public abstract boolean renderString(String text, int x, int y, int z, int width, int height, int color);

    public abstract boolean renderTexture(String id, int x, int y, int z, int width, int height);
}
