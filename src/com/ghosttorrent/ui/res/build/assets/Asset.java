package com.ghosttorrent.ui.res.build.assets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Asset {

    private String packageName, packagePath;
    protected Map<String, Object> variables;

    public Asset(){
        packageName = "generated";
        packagePath = System.getProperty("user.dir")+"/src/";
        variables = new HashMap<>();
    }

    public abstract String getName();

    public String getPackage(){
        return packageName;
    }

    private String getPackagePath(){
        return packagePath+getPackage().replaceAll("\\.", "/")+"/"+getName()+".java";
    }

    public void generate(){
        try{
            File file = new File(getPackagePath());
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            System.out.println(getPackagePath());
            FileWriter writer = new FileWriter(file);
            writer.write("package "+getPackage()+";\n\n");
            writer.write("/*********************************/\n");
            writer.write("/*   Auto generated by Builder   */\n");
            writer.write("/*********************************/\n");
            writer.write("public class "+getName()+" {\n\n");

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
