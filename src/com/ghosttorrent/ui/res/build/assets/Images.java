package com.ghosttorrent.ui.res.build.assets;

import java.io.File;

public class Images extends Assets {

    public Images(){
        File dir = new File(getClass().getResource("/image").getFile());

        for(File d : dir.listFiles()){
            for(File file : d.listFiles()){
                String name = file.getName().split("\\.")[0];
                variables.add(new Variable(name, name.hashCode(), "int"));
            }
        }
    }

    @Override
    public String getName(){
        return "Images";
    }
}
