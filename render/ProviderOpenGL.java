package jgui.render;

import java.util.Stack;
import jgui.FontInfo;
import jgui.RenderProvider;

public class ProviderOpenGL extends RenderProvider {
    protected final Stack<Integer> colorStack = new Stack<>();
    protected final Stack<String> textureStack = new Stack<>();
    protected final Stack<Float> rotationStack = new Stack<>();
    protected final Stack<ShapeType> shapeTypeStack = new Stack<>();

    protected ProviderOpenGL(FontInfo defaultFont) {
        super(defaultFont);
    }

    public static ProviderOpenGL initialize(String defaultFont) {
        return new ProviderOpenGL(new FontInfo(defaultFont));
    }

    @Override
    public boolean pushColor(int color){
        return false; //todo
    }

    @Override
    public boolean popColor(){
        return false; //todo
    }

    @Override
    public boolean pushTexture(String id){
        return false; //todo
    }

    @Override
    public boolean popTexture(){
        return false; //todo
    }

    @Override
    public boolean pushRotation(float angle) {
        return false; //todo
    }

    @Override
    public boolean popRotation() {
        return false; //todo
    }

    @Override
    public boolean pushShapeType(ShapeType type) {
        return false; //todo
    }

    @Override
    public boolean popShapeType() {
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
