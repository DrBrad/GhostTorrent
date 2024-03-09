package com.ghosttorrent.libs.ui.utils.inter;

import com.ghosttorrent.R;
import com.ghosttorrent.libs.ui.res.loader.AssetLoader;
import com.ghosttorrent.libs.ui.res.views.MenuBar;
import com.ghosttorrent.libs.ui.utils.Bundle;
import com.ghosttorrent.libs.ui.utils.Intent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

public abstract class Application {

    protected JFrame frame;
    protected R R;
    protected MenuBar menu;
    protected AssetLoader resources;

    public Application(){
        R = new R();
        resources = new AssetLoader(R);
        frame = new JFrame();
        frame.setTitle(findStringById(R.string.app_name));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void launch(){
        onCreate();
        frame.setVisible(true);
    }

    public void onCreate(){
    }

    public void onDestroy(){
        System.exit(0);
    }

    public void startActivity(Activity activity){
        startActivity(activity, new Intent());
    }

    public void startActivity(Activity activity, Intent intent){
        try{
            Field f = Activity.class.getDeclaredField("application");
            f.setAccessible(true);
            f.set(activity, this);

            f = Activity.class.getDeclaredField("R");
            f.setAccessible(true);
            f.set(activity, R);

            JPanel root = new JPanel();
            frame.setContentPane(root);

            activity.onCreate(intent.getBundle());

        }catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public JFrame getFrame(){
        return frame;
    }

    public void setToolbar(int id){
        try{
            menu = (MenuBar) resources.inflate("menu", id);
            frame.setJMenuBar(menu);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void startDialog(Dialog dialog){
        startDialog(dialog, new Bundle());
    }

    public void startDialog(Dialog dialog, Bundle bundle){
        try{
            Field f = Dialog.class.getDeclaredField("application");
            f.setAccessible(true);
            f.set(dialog, this);

            f = Dialog.class.getDeclaredField("R");
            f.setAccessible(true);
            f.set(dialog, R);

            dialog.onCreate(bundle);

        }catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public JComponent findViewById(int id){
        return menu.findViewById(id);
    }

    public Color findColorById(int id){
        return (Color) resources.findById("color", id);
    }

    public String findStringById(int id){
        return (String) resources.findById("string", id);
    }

    public BufferedImage findImageById(int id){
        return (BufferedImage) resources.findById("image", id);
    }
}
