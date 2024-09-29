package jgui.render.providers;

import jgui.render.FontInfo;
import jgui.render.MouseCursor;
import jgui.render.RenderProvider;
import jgui.render.ShapeType;

public class ProviderOpenGL extends RenderProvider {

    protected ProviderOpenGL(FontInfo defaultFont) {
        super(defaultFont);
    }

    public static ProviderOpenGL initialize(String defaultFont) {
        return new ProviderOpenGL(new FontInfo(defaultFont));
    }

    @Override
    protected boolean applyFont(FontInfo font){
        return false; //todo
    }

    @Override
    protected boolean applyCursor(MouseCursor cusros){
        return false; //todo
    }

    @Override
    public boolean applyColor(int color){
        return false; //todo
    }

    @Override
    public boolean applyTexture(String id){
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
