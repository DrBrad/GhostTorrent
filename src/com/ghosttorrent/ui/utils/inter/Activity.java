package com.ghosttorrent.ui.utils.inter;

import com.ghosttorrent.R;
import com.ghosttorrent.ui.res.views.View;
import com.ghosttorrent.ui.utils.Bundle;
import com.ghosttorrent.ui.res.views.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Activity {

    private Application application;
    protected R R;
    private Panel root;

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

    public Panel getRoot(){
        return root;
    }

    public void setContentView(int id){
        root = (Panel) application.resources.inflate("layout", id);
        application.frame.setContentPane(root);
    }

    public View inflateLayout(int id){
        return application.resources.inflate("layout", id);
    }

    public JComponent findViewById(int id){
        return root.findViewById(id);
        //return (JComponent) application.resources.findById("id", id);
    }

    public String findStringById(int id){
        return (String) application.resources.findById("string", id);
    }

    public Color findColorById(int id){
        return (Color) application.resources.findById("color", id);
    }

    public BufferedImage findImageById(int id){
        return (BufferedImage) application.resources.findById("image", id);
    }
}
