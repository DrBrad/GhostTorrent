package com.ghosttorrent.ui.res.build;

import com.ghosttorrent.ui.res.build.assets.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Builder {

    public Ids ids;

    public Builder(){
        ids = new Ids();
        Assets[] assets = new Assets[]{
                new Res(),
                new Colors(),
                new Strings(),
                new Images(),
                new Menus(this),
                new Layouts(this)
        };

        for(Assets asset : assets){
            generate(asset);
        }

        generate(ids);
    }

    public void generate(Assets asset){
        try{
            File file = new File(asset.getPackagePath());
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            String extend = (asset.getExtend() == null) ? "" : " extends "+asset.getExtend();
            String implement = (asset.getImplements() == null) ? "" : " extends "+asset.getImplements();

            System.out.println(asset.getPackagePath());
            FileWriter writer = new FileWriter(file);
            writer.write("package "+asset.getPackage()+";\n\n");
            writer.write("/*********************************/\n");
            writer.write("/*   Auto generated by Builder   */\n");
            writer.write("/*********************************/\n");
            writer.write("public class "+asset.getName()+extend+implement+" {\n\n");

            for(Assets.Variable variable : asset.getVariables()){
                if(variable.getValue() == null){
                    writer.write("    public "+variable.getType()+" "+variable.getKey()+";\n");
                    continue;
                }

                writer.write("    public "+variable.getType()+" "+variable.getKey()+" = "+variable.getValue()+";\n");
            }

            writer.write("}\n");

            writer.flush();
            writer.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
