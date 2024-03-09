package com.ghosttorrent.libs.ui.res.build.assets;

public class Res extends Assets {

    public Res(){
        imports.add("generated.*");
        variables.add(new Variable("color", "new Colors()", "Colors"));
        variables.add(new Variable("string", "new Strings()", "Strings"));
        variables.add(new Variable("image", "new Images()", "Images"));
        variables.add(new Variable("menu", "new Menus()", "Menus"));
        variables.add(new Variable("layout", "new Layouts()", "Layouts"));
        variables.add(new Variable("id", "new Ids()", "Ids"));
    }

    @Override
    public String getPackageName(){
        return "com.ghosttorrent";
    }

    @Override
    public String getName(){
        return "R";
    }
}
