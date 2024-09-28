package jgui;

public abstract class RenderProvider {
    protected static RenderProvider current = null;

    public static RenderProvider set(RenderProvider provider){
        RenderProvider previous = current;

        current = provider;

        return previous;
    }

    public static RenderProvider get(){
        return current;
    }

    public abstract boolean renderTexture(String id, int x, int y, int z, int width, int height);
}
