package com.ghosttorrent.libs.ui.utils.inter;

import com.ghosttorrent.R;
import com.ghosttorrent.libs.ui.res.views.Panel;
import com.ghosttorrent.libs.ui.res.views.View;
import com.ghosttorrent.libs.ui.utils.Bundle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Dialog {

    protected Application application;
    protected R R;
    protected JFrame frame;
    protected Panel root;
    protected List<DialogCloseListener> listeners;

    public Dialog(){
        listeners = new ArrayList<>();
        frame = new JFrame();
        //frame.setTitle(findStringById(R.string.app_name));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void onCreate(Bundle bundle){
    }

    public void onDestroy(){
        frame.dispose();
    }

    public void close(){
        onDestroy();
    }

    public void closeWithIntent(Bundle bundle){
        onDestroy();

        if(!listeners.isEmpty()){
            for(DialogCloseListener listener : listeners){
                listener.onClose(bundle);
            }
        }
    }

    public JFrame getFrame(){
        return frame;
    }

    public Panel getRoot(){
        return root;
    }

    public void addCloseListener(DialogCloseListener listener){
        listeners.add(listener);
    }

    public void removeCloseListener(DialogCloseListener listener){
        listeners.remove(listener);
    }

    public void setContentView(int id){
        try{
            root = (Panel) application.resources.inflate("layout", id);
            frame.setContentPane(root);

            int x;
            if(application.frame.getSize().width > frame.getSize().width){
                x = application.frame.getLocation().x+((application.frame.getSize().width-frame.getSize().width)/2);
            }else{
                x = application.frame.getLocation().x+((frame.getSize().width-application.frame.getSize().width)/2);
            }

            int y;
            if(application.frame.getSize().height > frame.getSize().height){
                y = application.frame.getLocation().y+((application.frame.getSize().height-frame.getSize().height)/2);
            }else{
                y = application.frame.getLocation().y+((frame.getSize().height-application.frame.getSize().height)/2);
            }

            frame.setLocation(x, y);

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
