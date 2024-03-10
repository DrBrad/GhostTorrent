package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.Bundle;
import com.ghosttorrent.libs.ui.utils.inter.Dialog;
import com.ghosttorrent.libs.ui.utils.inter.DialogCloseListener;
import com.ghosttorrent.ui.listeners.OpenTorrentListener;
import com.ghosttorrent.libs.ui.utils.inter.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

        ((JMenuItem) findViewById(R.id.menu_item_new_torrent)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Dialog dialog = new NewTorrentDialog();
                dialog.addCloseListener(new DialogCloseListener(){
                    @Override
                    public void onClose(Bundle bundle){
                        System.out.println("CLOSED");
                    }
                });
                startDialog(dialog);
            }
        });

        ((JMenuItem) findViewById(R.id.menu_item_open_url)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Dialog dialog = new OpenURLDialog();
                dialog.addCloseListener(new DialogCloseListener(){
                    @Override
                    public void onClose(Bundle bundle){
                        if(bundle.containsKey("url")){
                            System.out.println(bundle.getString("url"));
                        }
                    }
                });
                startDialog(dialog);
            }
        });

        ((JMenuItem) findViewById(R.id.menu_item_quit)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                close();
            }
        });


        ((JMenuItem) findViewById(R.id.menu_item_donate)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                    try{
                        Desktop.getDesktop().browse(new URI("http://ghosttorrent.com/donate"));
                    }catch(URISyntaxException | IOException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        ((JMenuItem) findViewById(R.id.menu_item_about)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                    try{
                        Desktop.getDesktop().browse(new URI("http://ghosttorrent.com"));
                    }catch(URISyntaxException | IOException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        startActivity(new MainActivity());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
