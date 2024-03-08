package com.ghosttorrent.ui.utils.inter;

import com.ghosttorrent.ui.res.loader.Resources;
import com.ghosttorrent.ui.utils.Bundle;
import generated.R;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Activity {

    private Application application;
    protected R R;
    //private Resources resources;
    private JPanel root;

    public void onCreate(Bundle bundle){
    }

    public void onResume(){

    }

    public void onPause(){

    }

    public void onDestroy(){

    }

    public JFrame getFrame(){
        return application.getFrame();
    }

    public JPanel getRoot(){
        return root;
    }

    public void setContentView(int id){
        root = (JPanel) application.resources.inflate("layout", id);
        application.frame.add(root);
    }

    public JComponent findViewById(int id){
        return (JComponent) application.resources.findById("id", id);
    }

    public Color findColorById(int id){
        return (Color) application.resources.findById("color", id);
    }

    public BufferedImage findImageById(int id){
        return (BufferedImage) application.resources.findById("image", id);
    }
}
