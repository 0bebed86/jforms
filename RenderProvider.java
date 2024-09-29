package jgui;

import java.util.Stack;

public abstract class RenderProvider {

    public static enum ShapeType {
        TRIANGLE(0), QUAD(1), CIRCLE(2);

        public int value;

        private ShapeType(int value) {
            this.value = value;
        }
    }

    protected FontInfo defaultFont = null;
    protected final Stack<FontInfo> fontStack = new Stack<>();
    protected final Stack<MouseCursor> cursorStack = new Stack<>();

    protected RenderProvider(FontInfo defaultFont) {
        this.defaultFont = defaultFont;
        this.fontStack.push(defaultFont);
    }

    protected <T> boolean stackPush(Stack<T> stack, T element) {
        if (element == null) {
            return false;
        }

        stack.push(element);

        return true;
    }

    protected <T> T stackPop(Stack<T> stack) {
        if (stack.empty()) {
            return null;
        }

        return stack.pop();
    }

    protected <T> T stackGet(Stack<T> stack, T defaultValue) {
        if (stack.empty()) {
            return defaultValue;
        }

        return stack.peek();
    }

    public boolean pushFont(FontInfo font) {
        return stackPush(fontStack, font);
    }

    public FontInfo popFont() {
        return stackPop(fontStack);
    }

    public FontInfo getFont() {
        return stackGet(fontStack, defaultFont);
    }

    public boolean pushCursor(MouseCursor cursor) {
        return stackPush(cursorStack, cursor);
    }

    public MouseCursor popCursor() {
        return stackPop(cursorStack);
    }

    public MouseCursor getCursor() {
        return stackGet(cursorStack, MouseCursor.DEFAULT);
    }

    public abstract boolean pushColor(int color);
    public abstract boolean popColor();

    public abstract boolean pushTexture(String id);
    public abstract boolean popTexture();

    public abstract boolean pushRotation(float angle);
    public abstract boolean popRotation();

    public abstract boolean pushShapeType(ShapeType type);
    public abstract boolean popShapeType();

    public abstract boolean renderShapeFilled(int x, int y, int z, int width, int height, int color);
    public abstract boolean renderShapeBorder(int x, int y, int z, int width, int height, int color);

    public abstract boolean renderString(String text, int x, int y, int z, int width, int height, int color);

    public abstract boolean renderTexture(String id, int x, int y, int z, int width, int height);
}
