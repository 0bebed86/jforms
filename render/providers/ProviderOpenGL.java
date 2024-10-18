package jforms.render.providers;

import jforms.render.Font;
import jforms.render.MouseCursor;
import jforms.render.RenderProvider;
import jforms.render.ShapeType;
import jforms.render.ViewPort;
import jforms.util.vector.Vector2T;

public class ProviderOpenGL extends RenderProvider {
    public static class FontClass extends Font {
        public FontClass(String filePath){
            super(filePath);
        }

        @Override
        public int getTextWidth(String text){
            return 0;
        }

        @Override
        public int getTextHeight(String text){
            return 0;
        }
    
        @Override
        public Vector2T<Integer> getTextSize(String text){
            return new Vector2T<Integer>(0, 0);
        }
    }

    protected ProviderOpenGL(Font defaultFont) {
        super(defaultFont);
    }

    public static ProviderOpenGL initialize(String defaultFont) {
        Font font = new FontClass(defaultFont);

        return new ProviderOpenGL(font);
    }

    @Override
    protected boolean applyFont(Font font) {
        return false; //todo
    }

    @Override
    protected boolean applyCursor(MouseCursor cusros) {
        return false; //todo
    }

    @Override
    protected boolean applyViewPort(ViewPort viewPort) {
        return false; //todo
    }

    @Override
    public boolean applyColor(int color) {
        return false; //todo
    }

    @Override
    public boolean applyTexture(String id) {
        return false; //todo
    }

    @Override
    public boolean applyRotation(float angle) {
        return false; //todo
    }

    @Override
    public boolean applyShapeType(ShapeType type) {
        return false; //todo
    }

    @Override
    public boolean renderShape(int x, int y, int z, int width, int height, int color, float rounding, boolean fill) {
        return false; //todo
    }

    @Override
    public boolean renderString(String text, int x, int y, int z, int width, int height, int color) {
        return false; //todo
    }

    @Override
    public boolean renderTexture(String id, int x, int y, int z, int width, int height) {
        return false; //todo
    }

}
