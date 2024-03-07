package com.ghosttorrent.ui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Res {

    public Map<String, ImageIcon> images;
    public Map<String, Color> colors;

    public Res(){
        loadImages();
        //loadStrings();
        loadColors();
    }

    private void loadStrings(){

    }

    private void loadColors(){
        colors = new HashMap<>();

        colors.put("primary", Color.decode("#24f262"));
        colors.put("secondary", Color.decode("#1abd4b"));
        colors.put("accent", Color.decode("#9939bf"));

        colors.put("background", Color.decode("#080d2b"));
        colors.put("background-secondary", Color.decode("#0c1133"));
        colors.put("background-shimmer", Color.decode("#161c45"));

        colors.put("text-primary", Color.decode("#ffffff"));
        colors.put("text-secondary", Color.decode("#cccccc"));
        /*
        File colors = new File(getClass().getResource("/styles/colors.yml").getFile());
        try{
            InputStream in = new FileInputStream(colors);

            byte[] buf = new byte[(int) colors.length()];
            in.read(buf);
        }catch(IOException e){
            e.printStackTrace();
        }
        */
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
