package com.ghosttorrent.ui.res.build.assets;

import java.util.HashMap;

public class Res extends Asset {

    public Res(){
        variables = new HashMap<>();
        variables.put("color", "Colors");//new Variable("color", "Colors", Variable.Escape.ESCAPE));
        variables.put("image", "Images");//new Variable("image", "Images", Variable.Escape.ESCAPE));
    }

    @Override
    public String getName(){
        return "R";
    }
}
