package com.ghosttorrent.ui;

import com.ghosttorrent.ui.utils.inter.Application;
import com.ghosttorrent.ui.utils.OLD.menus.MenuItem;
import com.ghosttorrent.ui.utils.OLD.menus.MenuOption;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GhostApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        setToolbar(R.menu.toolbar);

        frame.setTitle("Ghost Torrent");
        frame.setSize(500, 750);
        frame.setMinimumSize(new Dimension(500, 750));
        frame.setJMenuBar(createMenuBar());

        try{
            //startActivity(new MainActivity());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){

    }

    private JMenuBar createMenuBar(){
        JMenuBar menu = new JMenuBar();
        //menu.setBackground(R.colors.background_shimmer);
        menu.setBorder(new EmptyBorder(0, 0, 0, 0));

        /*
        for(MenuItem item : R.menus.get("menu").getAllItems()){
            menu.add(createMenu(item));
        }*/

        return menu;
    }

    private JMenu createMenu(MenuItem item){
        JMenu menu = new JMenu(item.getTitle());
        //menu.setForeground(R.colors.get("text-primary"));

        for(MenuOption option : item.getAllOptions()){
            JMenuItem i = new JMenuItem(option.getTitle());
            menu.add(i);
        }
        return menu;
    }
}
