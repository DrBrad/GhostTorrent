package com.ghosttorrent.ui.build;

import com.ghosttorrent.ui.build.assets.Asset;
import com.ghosttorrent.ui.build.assets.Colors;
import com.ghosttorrent.ui.build.assets.Images;
import com.ghosttorrent.ui.build.assets.Res;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Builder {

    private String packagePath;
    private String packageName;

    public Builder(){
        packageName = "generated";
        packagePath = System.getProperty("user.dir")+"/src/"+packageName;

        Asset[] assets = new Asset[]{
                new Res(),
                new Colors(),
                new Images()
        };

        for(Asset asset : assets){
            generate(asset.getName(), asset.getVariables());
        }
    }

    private void generate(String name, Map<String, Object> variables){
        try{
            System.out.println(packagePath+"/"+name);
            FileWriter writer = new FileWriter(packagePath+"/"+name+".java");
            writer.write("package "+packageName+";\n\n");
            writer.write("/*********************************/\n");
            writer.write("/*   Auto generated by Builder   */\n");
            writer.write("/*********************************/\n");
            writer.write("public class "+name+" {\n\n");

            for(String key : variables.keySet()){
                if(variables.get(key) instanceof Number){
                    writer.write("    public int "+key+" = "+variables.get(key)+";\n");
                    continue;
                }

                writer.write("    public "+variables.get(key)+" "+key+" = new "+variables.get(key)+"();\n");
            }

            writer.write("}\n");

            writer.flush();
            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
