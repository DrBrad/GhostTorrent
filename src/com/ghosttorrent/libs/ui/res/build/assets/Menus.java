package com.ghosttorrent.libs.ui.res.build.assets;

import com.ghosttorrent.libs.ui.res.build.AssetBuilder;

public class Menus extends ViewAsset {

    public Menus(AssetBuilder builder){
        super(builder, "menu");
    }

    @Override
    public String getName(){
        return "Menu";
    }
}
