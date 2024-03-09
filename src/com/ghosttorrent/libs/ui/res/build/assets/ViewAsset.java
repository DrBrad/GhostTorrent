package com.ghosttorrent.libs.ui.res.build.assets;

import com.ghosttorrent.libs.ui.res.build.Builder;

import java.io.File;

public class ViewAsset extends Assets {

    protected String res;

    public ViewAsset(Builder builder, String res){
        File dir = new File(getClass().getResource("/"+res).getFile());

        for(File file : dir.listFiles()){
            String name = file.getName().split("\\.")[0];
            variables.add(new Assets.Variable(name, name.hashCode(), "int"));
            builder.ids.parse(file);
        }
    }

    @Override
    public String getName(){
        return null;
    }
}
