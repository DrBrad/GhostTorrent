package com.ghosttorrent.ui.utils.inter;

import com.ghosttorrent.ui.utils.Intent;
import generated.R;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class Application {

    protected JFrame frame;
    protected R R;

    public Application(){
        R = new R();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LOAD RESOURCES
        //REFLECT THE GENERATED RESOURCES
        //ADD THEM TO THE MAP

        /*
        for(Field field : R.image.getClass().getFields()){
            try{
                System.out.println(field.get(R.image));
            }catch(Exception e){
                e.printStackTrace();
            }
        }*/





    }

    public void launch(){
        onCreate();
        frame.setVisible(true);
    }

    public abstract void onCreate();

    public abstract void onDestroy();

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

        f = Activity.class.getDeclaredField("root");
        f.setAccessible(true);
        f.set(activity, root);

        activity.onCreate(intent.getBundle());
    }

    public JFrame getFrame(){
        return frame;
    }
}
