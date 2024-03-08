package com.ghosttorrent.ui.res.build.assets;

import com.ghosttorrent.ui.res.build.Builder;

public class Menus extends ViewAsset {

    public Menus(Builder builder){
        super(builder, "menu");
    }

    @Override
    public String getName(){
        return "Menu";
    }
}
