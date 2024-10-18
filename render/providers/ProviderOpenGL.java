package jforms.render.providers;

import jforms.render.Font;
import jforms.render.MouseCursor;
import jforms.render.RenderProvider;
import jforms.render.ShapeType;
import jforms.render.ViewPort;

public class ProviderOpenGL extends RenderProvider {

    protected ProviderOpenGL(Font defaultFont) {
        super(defaultFont);
    }

    public static ProviderOpenGL initialize(String defaultFont) {
        return new ProviderOpenGL(new Font(defaultFont));
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
    public boolean renderShapeFilled(int x, int y, int z, int width, int height, int color) {
        return false; //todo
    }

    @Override
    public boolean renderShapeBorder(int x, int y, int z, int width, int height, int color) {
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
