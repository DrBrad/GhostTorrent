package com.ghosttorrent.ui.build.assets;

import com.ghosttorrent.ui.build.Variable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Images extends Asset {

    public Images(){
        variables = new HashMap<>();
        File dir = new File(getClass().getResource("/image/48").getFile());

        for(File file : dir.listFiles()){
            variables.put(file.getName().split("\\.")[0], 0);//new Variable(file.getName().split("\\.")[0], 0));
        }
    }

    @Override
    public String getName(){
        return "Images";
    }
}
