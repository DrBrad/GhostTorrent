package com.ghosttorrent;

import com.ghosttorrent.ui.GhostApplication;
import com.ghosttorrent.ui.utils.inter.Application;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        Application application = new GhostApplication();
        application.launch();


        /*
        FileDialog dialog = new FileDialog((Frame) null);
        dialog.setVisible(true);
        System.out.println(dialog.getFile());
        */

        //Kademlia kad = new Kademlia();
        //kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);
    }
}
