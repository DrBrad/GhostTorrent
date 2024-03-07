package com.ghosttorrent.ui.utils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Application {

    protected JFrame frame;

    public Application(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500,750);
    }

    public void launch(){
        onCreate();
        frame.setVisible(true);
    }

    public abstract void onCreate();

    public abstract void onDestroy();

    public void startActivity(Activity activity)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        startActivity(activity, null);
    }

    public void startActivity(Activity activity, Intent intent)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method setApplication = Activity.class.getDeclaredMethod("setApplication", Application.class);
        setApplication.setAccessible(true);
        setApplication.invoke(activity, this);

        JPanel root = new JPanel();
        frame.setContentPane(root);

        Method setRoot = Activity.class.getDeclaredMethod("setRoot", JPanel.class);
        setRoot.setAccessible(true);
        setRoot.invoke(activity, root);

        activity.onCreate(intent);
    }

    public JFrame getFrame(){
        return frame;
    }

    public void setSize(int width, int height){
        frame.setSize(width, height);
    }

    public void setTitle(String title){
        frame.setTitle(title);
    }

    /*
    public void setTitle(String title){
        frame.setTitle(title);
    }
    */
}
