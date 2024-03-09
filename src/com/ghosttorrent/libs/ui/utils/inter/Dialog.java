package com.ghosttorrent.libs.ui.utils.inter;

import com.ghosttorrent.R;
import com.ghosttorrent.libs.ui.res.views.Panel;
import com.ghosttorrent.libs.ui.res.views.View;
import com.ghosttorrent.libs.ui.utils.Bundle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Dialog {

    protected Application application;
    protected R R;
    protected JFrame frame;
    protected Panel root;

    public Dialog(){
        frame = new JFrame();
        //frame.setTitle(findStringById(R.string.app_name));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void onCreate(Bundle bundle){
    }

    public void onDestroy(){
        frame.dispose();
    }

    public JFrame getFrame(){
        return frame;
    }

    public Panel getRoot(){
        return root;
    }

    public void setContentView(int id){
        try{
            root = (Panel) application.resources.inflate("layout", id);
            frame.setContentPane(root);
            frame.setLocation(application.frame.getLocation());
            frame.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public View inflateLayout(int id){
        try{
            return application.resources.inflate("layout", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
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
