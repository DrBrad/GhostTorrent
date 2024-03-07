package com.ghosttorrent.ui.res.build.assets;

import java.util.HashMap;

public class Res extends Asset {

    public Res(){
        variables = new HashMap<>();
        variables.put("color", "Colors");
        variables.put("image", "Images");
        variables.put("menu", "Menus");
    }

    @Override
    public String getName(){
        return "R";
    }
}
