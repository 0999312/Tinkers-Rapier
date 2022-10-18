package cn.mcmod.tinker_rapier.data;

import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

public class RapierTextureProvider extends AbstractPartSpriteProvider{

    public RapierTextureProvider(String modID) {
        super(modID);
    }

    @Override
    protected void addAllSpites() {
        addHead("slender_blade");
        
        buildTool("rapier").addBreakableHead("blade").addBinding("guard_cup").addHandle("guard").addHandle("handle");
        buildTool("estoc").addBreakableHead("blade").addHandle("guard_estoc").addHandle("handle");
    }

    @Override
    public String getName() {
        return "Tinker's Rapier Tool Texture";
    }

}
