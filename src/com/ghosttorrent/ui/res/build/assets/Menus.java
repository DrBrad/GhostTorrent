package com.ghosttorrent.ui.res.build.assets;

import java.io.File;

public class Menus extends Asset {

    private Ids ids;

    public Menus(){
        File dir = new File(getClass().getResource("/menu").getFile());
        ids = new Ids();

        for(File file : dir.listFiles()){
            String name = file.getName().split("\\.")[0];
            variables.add(new Variable(name, Math.abs(name.hashCode()), "int"));
            ids.parse(file);
        }
    }

    public Ids getIds(){
        return ids;
    }

    @Override
    public String getName(){
        return "Menus";
    }
}
