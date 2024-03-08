package com.ghosttorrent.ui;

import com.ghosttorrent.ui.utils.inter.Application;
import unet.kad4.libs.bencode.Bencoder;
import unet.kad4.libs.bencode.variables.BencodeObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        ((JMenuItem) findViewById(R.id.menu_item_open)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FileDialog dialog = new FileDialog((Frame) null);
                dialog.setVisible(true);

                File file = new File(dialog.getDirectory()+"/"+dialog.getFile());

                try{
                    InputStream in = new FileInputStream(file);
                    byte[] buf = new byte[(int) file.length()];
                    in.read(buf);

                    BencodeObject ben = new BencodeObject(buf, 0);

                    System.out.println(ben);

                }catch(IOException ex){
                    ex.printStackTrace();
                }

            }
        });

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
