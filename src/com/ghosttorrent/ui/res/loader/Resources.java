package com.ghosttorrent.ui.res.loader;

import com.ghosttorrent.ui.res.loader.assets.Asset;
import com.ghosttorrent.ui.res.loader.assets.Colors;
import com.ghosttorrent.ui.res.loader.assets.Images;
import com.ghosttorrent.ui.res.loader.assets.Menus;
import generated.R;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    public Map<String, Asset> assets;

    public Resources(R R){
        assets = new HashMap<>();
        assets.put("color", new Colors(R.color));
        assets.put("image", new Images(R.image));
        assets.put("menu", new Menus(R.menu));
        //assets.put("id", );
    }

    public Object findById(String asset, int id){
        return assets.get(asset).get(id);
    }

    public JComponent inflate(String asset, int id){
        String name = (String) assets.get(asset).get(id);
        try{
            System.out.println(name);
            Class<?> c = Class.forName("generated."+asset+"."+name);

            File file = new File(getClass().getResource("/menu/"+name+".xml").getFile());



        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }




        return null;
    }
}
