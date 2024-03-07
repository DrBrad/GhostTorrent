package com.ghosttorrent.ui;

import com.ghosttorrent.ui.utils.Application;

import javax.swing.*;
import java.awt.*;

public class GhostApplication extends Application {

    @Override
    public void onCreate(){
        setTitle("Ghost Torrent");
        setSize(500, 750);
        frame.setJMenuBar(createMenuBar());

        try{
            startActivity(new MainActivity());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){

    }

    private JMenuBar createMenuBar(){
        JMenuBar menu = new JMenuBar();

        menu.add(createMenu("File", new String[]{
                "Open",
                "Open URL",
                "New",
                "Start All",
                "Pause All",
                "Quit"
        }));

        menu.add(createMenu("Edit", new String[]{
                "Select All",
                "Deselect All",
                "Preferences"
        }));

        menu.add(createMenu("Torrent", new String[]{
                "Properties",
                "Open Folder",
                "Start"
                //ECT...
        }));

        menu.add(createMenu("View", new String[]{
                "Compact View",
                "Toolbar",
                "Filterbar",
                "Statusbar"
                //ECT...
        }));

        menu.add(createMenu("Help", new String[]{
                "Message Log",
                "Statistics",
                "Donate",
                "Contents",
                "About"
        }));

        return menu;
    }

    private JMenu createMenu(String title, String[] options){
        JMenu menu = new JMenu(title);
        for(String option : options){
            menu.add(new JMenuItem(option));
        }
        return menu;
    }
}
