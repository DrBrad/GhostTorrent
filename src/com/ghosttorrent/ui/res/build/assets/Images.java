package com.ghosttorrent.ui.res.build.assets;

import java.io.File;

public class Images extends Asset {

    public Images(){
        File dir = new File(getClass().getResource("/image/48").getFile());

        for(File file : dir.listFiles()){
            String name = file.getName().split("\\.")[0];
            variables.add(new Variable(name, Math.abs(name.hashCode()), "int"));
        }
    }

    @Override
    public String getName(){
        return "Images";
    }
}
