package com.ghosttorrent.ui;

import com.ghosttorrent.torrent.Torrent;
import com.ghosttorrent.ui.listeners.OpenTorrentListener;
import com.ghosttorrent.ui.utils.inter.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GhostApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        setToolbar(R.menu.toolbar);

        frame.setSize(500, 750);
        frame.setMinimumSize(new Dimension(500, 750));
        //menu.setBorder(new EmptyBorder(0, 0, 0, 0));
        //menu.setBackground(R.colors.background_shimmer);

        //menu.setForeground(R.colors.get("text-primary"));

        ((JMenuItem) findViewById(R.id.menu_item_open)).addActionListener(new OpenTorrentListener());

        ((JMenuItem) findViewById(R.id.menu_item_quit)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });


        try{
            startActivity(new MainActivity());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){

    }
}
