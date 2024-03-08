package com.ghosttorrent.ui.utils.inter;

import com.ghosttorrent.ui.res.loader.Resources;
import com.ghosttorrent.ui.res.views.Menu;
import com.ghosttorrent.ui.res.views.MenuBar;
import com.ghosttorrent.ui.utils.Intent;
import generated.R;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class Application {

    protected JFrame frame;
    protected MenuBar menu;
    protected R R;
    protected Resources resources;

    public Application(){
        R = new R();
        resources = new Resources(R);
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
    }

    public void startActivity(Activity activity)throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        startActivity(activity, new Intent());
    }

    public void startActivity(Activity activity, Intent intent)throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field f = Activity.class.getDeclaredField("application");
        f.setAccessible(true);
        f.set(activity, this);

        f = Activity.class.getDeclaredField("R");
        f.setAccessible(true);
        f.set(activity, R);

        JPanel root = new JPanel();
        frame.setContentPane(root);

        //f = Activity.class.getDeclaredField("root");
        //f.setAccessible(true);
        //f.set(activity, root);

        activity.onCreate(intent.getBundle());
    }

    public JFrame getFrame(){
        return frame;
    }

    public void setToolbar(int id){
        menu = (MenuBar) resources.inflate("menu", id);
        frame.setJMenuBar(menu);
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
