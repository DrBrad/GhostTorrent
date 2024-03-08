package com.ghosttorrent;

import com.ghosttorrent.ui.GhostApplication;
import com.ghosttorrent.ui.utils.inter.Application;

import javax.swing.*;

public class Main {

    //HONESTLY WE COULD JUST MAKE OUR OWN COMPONENTS AND FILL WITH REFLECT - TODO
    //TORRENT I/O STREAM SHOULD BE MADE...

    public static void main(String[] args)throws Exception {
        boolean gui = true;

        if(args.length > 0){
            for(String arg : args){
                switch(arg){
                    case "--no-gui":
                        gui = false;
                        break;
                }
            }
        }

        //CHECK CONFIG
        //Settings.TORRENT_DIR = System.getProperty("user.home");

        //String home = System.getProperty("user.home");
        //System.out.println(home);

        //new Builder();

        if(gui){
            System.setProperty("sun.java2d.opengl", "true");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Application application = new GhostApplication();
            application.launch();
        }
    }
}
