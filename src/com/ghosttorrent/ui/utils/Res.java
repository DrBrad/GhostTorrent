package com.ghosttorrent.ui.utils;

import com.ghosttorrent.ui.build.assets.Colors;
import com.ghosttorrent.ui.utils.res.menus.Menu;

import javax.swing.*;
import java.util.Map;

public class Res {

    public Map<String, ImageIcon> images;
    public Colors colors;
    public Map<String, Menu> menus;

    public Res(){
        //findViewById(R.id.)

        //loadImages();
        //loadStrings();
        //loadColors();
        //loadMenu();
    }

    /*
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

    private void loadStrings(){

    }

    private void loadColors(){
        File file = new File(getClass().getResource("/style/colors.xml").getFile());
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            colors = new Colors(doc);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void loadMenu(){
        menus = new HashMap<>();

        File dir = new File(getClass().getResource("/menu").getFile());

        for(File file : dir.listFiles()){
            try{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(file);
                menus.put(file.getName().split("\\.")[0], new Menu(doc));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    */
}
