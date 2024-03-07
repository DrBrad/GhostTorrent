package com.ghosttorrent;

import com.ghosttorrent.ui.GhostApplication;
import com.ghosttorrent.ui.utils.inter.Application;

public class Main {

    public static void main(String[] args){
        Application application = new GhostApplication();
        application.launch();

        //Kademlia kad = new Kademlia();
        //kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);
    }
}
