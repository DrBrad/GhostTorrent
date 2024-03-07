package com.ghosttorrent.ui.utils.inter;

import com.ghosttorrent.ui.utils.Intent;
import com.ghosttorrent.ui.utils.Res;

import javax.swing.*;

public abstract class Activity {

    private Application application;
    protected Res R;
    private JPanel root;

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
