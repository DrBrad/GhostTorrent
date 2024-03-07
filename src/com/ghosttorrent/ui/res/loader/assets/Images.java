package com.ghosttorrent.ui.res.loader.assets;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class Images extends Asset {

    public Images(generated.Images images){
        File dir = new File(getClass().getResource("/image/48").getFile());

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
    }
}
