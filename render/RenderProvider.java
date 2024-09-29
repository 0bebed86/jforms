package jgui.render;

import jgui.FontInfo;
import jgui.render.RenderProvider.ShapeType;

public abstract class RenderProvider {

    public static enum ShapeType {
        TRIANGLE(0), QUAD(1), CIRCLE(2);

        public int value;

        private ShapeType(int value) {
            this.value = value;
        }
    }

    protected static interface IApplier<T> {

        boolean apply(T element);
    }

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

            if(apply && applier != null){
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

    protected FontInfo defaultFont = null;
    protected final Stack<FontInfo> fontStack = new Stack<>();
    protected final Stack<MouseCursor> cursorStack = new Stack<>();
    protected final Stack<Integer> colorStack = new Stack<>();
    protected final Stack<String> textureStack = new Stack<>();
    protected final Stack<Float> rotationStack = new Stack<>();
    protected final Stack<ShapeType> shapeTypeStack = new Stack<>();

    protected RenderProvider(FontInfo defaultFont) {
        this.defaultFont = defaultFont;
        this.fontStack.push(defaultFont);
    }

    protected abstract boolean applyFont(FontInfo font);

    public boolean pushFont(FontInfo font, boolean apply) {
        return fontStack.pushApply(this::applyFont, font, apply);
    }

    public FontInfo popFont(boolean apply) {
        return fontStack.popApply(this::applyFont, true);
    }

    public FontInfo getFont() {
        return fontStack.get(defaultFont);
    }

    protected abstract boolean applyCursor(MouseCursor cursor);

    public boolean pushCursor(MouseCursor cursor, boolean apply) {
        return cursorStack.pushApply(this::applyCursor, cursor, apply);
    }

    public MouseCursor popCursor(boolean apply) {
        return cursorStack.popApply(this::applyCursor, true);
    }

    public MouseCursor getCursor() {
        return cursorStack.get(MouseCursor.DEFAULT);
    }

    protected abstract boolean applyColor(int color);

    public boolean pushColor(int color, boolean apply) {
        return colorStack.pushApply(this::applyColor, color, apply);
    }

    public int popColor(boolean apply) {
        return colorStack.popApply(this::applyColor, true);
    }

    public int getColor() {
        return colorStack.get(-1);
    }

    protected abstract boolean applyTexture(String id);

    public boolean pushTexture(String id, boolean apply) {
        return textureStack.pushApply(this::applyTexture, id, apply);
    }

    public String popTexture(boolean apply) {
        return textureStack.popApply(this::applyTexture, true);
    }

    public String getTexture() {
        return textureStack.get(null);
    }

    protected abstract boolean applyRotation(float angle);

    public boolean pushRotation(float angle, boolean apply) {
        return rotationStack.pushApply(this::applyRotation, angle, apply);
    }

    public float popRotation(boolean apply) {
        return rotationStack.popApply(this::applyRotation, true);
    }

    public float getRotation() {
        return rotationStack.get(0.0f);
    }

    protected abstract boolean applyShapeType(ShapeType type);

    public boolean pushShapeType(ShapeType type, boolean apply) {
        return shapeTypeStack.pushApply(this::applyShapeType, type, apply);

    }

    public ShapeType popShapeType(boolean apply) {
        return shapeTypeStack.popApply(this::applyShapeType, true);
    }

    public ShapeType getShapeType() {
        return shapeTypeStack.get(null);
    }

    public abstract boolean renderShapeFilled(int x, int y, int z, int width, int height, int color);

    public abstract boolean renderShapeBorder(int x, int y, int z, int width, int height, int color);

    public abstract boolean renderString(String text, int x, int y, int z, int width, int height, int color);

    public abstract boolean renderTexture(String id, int x, int y, int z, int width, int height);
}
