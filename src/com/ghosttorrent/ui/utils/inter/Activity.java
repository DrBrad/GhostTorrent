package com.ghosttorrent.ui.utils.inter;

import com.ghosttorrent.ui.utils.Bundle;
import generated.R;

import javax.swing.*;

public abstract class Activity {

    private Application application;
    protected R R;
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

    public void setContentView(){
    }

    public JComponent findViewById(String id){
        return null;
    }
}
