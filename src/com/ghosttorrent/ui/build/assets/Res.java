package com.ghosttorrent.ui.build.assets;

import com.ghosttorrent.ui.build.Variable;
import com.ghosttorrent.ui.build.assets.Asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
