package com.ghosttorrent.ui.build.assets;

import com.ghosttorrent.ui.build.Variable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Images extends Asset {

    private List<Variable> variables;

    public Images(){
        variables = new ArrayList<>();
        File dir = new File(getClass().getResource("/image/48").getFile());

        for(File file : dir.listFiles()){
            variables.add(new Variable(file.getName().split("\\.")[0], 0));
        }
    }

    @Override
    public List<Variable> getVariables(){
        return variables;
    }

    @Override
    public String getName(){
        return "Images";
    }
}
