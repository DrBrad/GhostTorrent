package com.ghosttorrent.ui.utils;

import javax.swing.*;

public abstract class Activity {

    private Application application;
    private JPanel root;

    private void setApplication(Application application){
        this.application = application;
    }

    private void setRoot(JPanel root){
        this.root = root;
    }

    public abstract void onCreate(Intent intent);

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
}
