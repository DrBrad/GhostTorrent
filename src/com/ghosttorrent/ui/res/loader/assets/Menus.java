package com.ghosttorrent.ui.res.loader.assets;

import java.io.File;
import java.lang.reflect.Field;

public class Menus extends Asset {

    public Menus(generated.Menus menus){
        File dir = new File(getClass().getResource("/menu").getFile());

        for(Field field : menus.getClass().getFields()){
            File file = new File(dir, field.getName()+".xml");
            if(!file.exists()){
                continue;
            }

            try{
                variables.put((int) field.get(menus), field.getName());
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
