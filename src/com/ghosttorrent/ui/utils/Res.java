package com.ghosttorrent.ui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Res {

    public Map<String, ImageIcon> images;

    public Res(){
        loadImages();
    }

    private void loadImages(){
        images = new HashMap<>();
        File dir = new File(getClass().getResource("/images/48").getFile());

        for(File file : dir.listFiles()){
            try{
                BufferedImage img = ImageIO.read(file);
                images.put(file.getName().split("\\.")[0], new ImageIcon(img));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
