package jgui.render;

import jgui.RenderProvider;

public class ProviderOpenGL extends RenderProvider {
    public ProviderOpenGL(){
        
    }

    @Override
    public boolean renderTexture(String id, int x, int y, int z, int width, int height) {
        return false; //todo
    }
}
