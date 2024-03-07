package com.ghosttorrent.ui.build;

import com.ghosttorrent.ui.build.assets.Asset;

import java.util.ArrayList;
import java.util.List;

public class Res extends Asset {

    private List<Variable> variables;

    public Res(){
        variables = new ArrayList<>();
        variables.add(new Variable("color", "Colors", Variable.Escape.ESCAPE));
        variables.add(new Variable("image", "Images", Variable.Escape.ESCAPE));
    }

    @Override
    public List<Variable> getVariables(){
        return variables;
    }

    @Override
    public String getName(){
        return "R";
    }
}
