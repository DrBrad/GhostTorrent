package com.ghosttorrent.ui.res.loader.assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class Images extends Assets {

    public Images(generated.Images images){
        File dir = new File(getClass().getResource("/image").getFile());

        for(File d : dir.listFiles()){
            for(File file : d.listFiles()){

                try{
                    int id = (int) images.getClass().getField(file.getName().split("\\.")[0]).get(images);
                    variables.put(id, ImageIO.read(file));//Color.decode(element.getAttribute("value")));
                }catch(NoSuchFieldException | IllegalAccessException | IOException e){
                    e.printStackTrace();
                }
            }
        }
        /*
        for(Field field : images.getClass().getFields()){
            File file = new File(dir, field.getName()+".png");
            if(!file.exists()){
                continue;
            }

            try{
                variables.put((int) field.get(images), ImageIO.read(file));
            }catch(IllegalAccessException | IOException e){
                e.printStackTrace();
            }
        }
        */
    }
}
