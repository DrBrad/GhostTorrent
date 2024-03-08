package com.ghosttorrent.ui.res.build.assets;

public class Res extends Asset {

    public Res(){
        variables.add(new Variable("color", "new Colors()", "Colors"));
        variables.add(new Variable("image", "new Images()", "Images"));
        variables.add(new Variable("menu", "new Menus()", "Menus"));
        variables.add(new Variable("id", null, "com.ghosttorrent.ui.res.Ids"));
    }

    @Override
    public String getName(){
        return "R";
    }
}
