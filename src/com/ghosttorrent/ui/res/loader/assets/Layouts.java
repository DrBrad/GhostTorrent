package com.ghosttorrent.ui.res.loader.assets;

import java.io.File;
import java.lang.reflect.Field;

public class Layouts extends Assets {

    public Layouts(generated.Layouts layouts){
        File dir = new File(getClass().getResource("/layout").getFile());

        for(Field field : layouts.getClass().getFields()){
            File file = new File(dir, field.getName()+".xml");
            if(!file.exists()){
                continue;
            }

            try{
                variables.put((int) field.get(layouts), field.getName());
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
