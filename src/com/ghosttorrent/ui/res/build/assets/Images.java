package com.ghosttorrent.ui.res.build.assets;

import java.io.File;
import java.util.HashMap;

public class Images extends Asset {

    public Images(){
        variables = new HashMap<>();
        File dir = new File(getClass().getResource("/image/48").getFile());

        int i = 0;
        for(File file : dir.listFiles()){
            variables.put(file.getName().split("\\.")[0], i);
            i++;
        }
    }

    @Override
    public String getName(){
        return "Images";
    }
}