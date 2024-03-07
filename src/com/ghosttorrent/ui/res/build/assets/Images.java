package com.ghosttorrent.ui.res.build.assets;

import java.io.File;

public class Images extends Asset {

    public Images(){
        File dir = new File(getClass().getResource("/image/48").getFile());

        int i = 0;
        for(File file : dir.listFiles()){
            variables.add(new Variable(file.getName().split("\\.")[0], i, "int"));
            i++;
        }
    }

    @Override
    public String getName(){
        return "Images";
    }
}
