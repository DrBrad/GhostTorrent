package com.ghosttorrent.libs.ui.utils.inter;

import com.ghosttorrent.R;
import com.ghosttorrent.libs.ui.res.views.View;
import com.ghosttorrent.libs.ui.utils.Bundle;
import com.ghosttorrent.libs.ui.res.views.Panel;
import com.ghosttorrent.libs.ui.utils.Intent;

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
        try{
            root = (Panel) application.resources.inflate("layout", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        application.frame.setContentPane(root);
    }

    public View inflateLayout(int id){
        try{
            return application.resources.inflate("layout", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void startActivity(Activity activity){
        application.startActivity(activity);
    }

    public void startActivity(Activity activity, Intent intent){
        application.startActivity(activity, intent);
    }

    public void startDialog(Dialog dialog){
        application.startDialog(dialog, new Bundle());
    }

    public void startDialog(Dialog dialog, Bundle bundle){
        application.startDialog(dialog, bundle);
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
