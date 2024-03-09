package com.ghosttorrent.ui;

import com.ghosttorrent.ui.listeners.OpenTorrentListener;
import com.ghosttorrent.libs.ui.utils.inter.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GhostApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        setToolbar(R.menu.toolbar);

        frame.setSize(500, 800);
        frame.setMinimumSize(new Dimension(500, 800));
        //menu.setBorder(new EmptyBorder(0, 0, 0, 0));
        //menu.setBackground(R.colors.background_shimmer);

        //menu.setForeground(R.colors.get("text-primary"));

        ((JMenuItem) findViewById(R.id.menu_item_open)).addActionListener(new OpenTorrentListener());

        ((JMenuItem) findViewById(R.id.menu_item_open_url)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                startDialog(new OpenURLDialog());
            }
        });

        ((JMenuItem) findViewById(R.id.menu_item_quit)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                onDestroy();
            }
        });

        startActivity(new MainActivity());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
