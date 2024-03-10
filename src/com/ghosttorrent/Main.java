package com.ghosttorrent;

import com.ghosttorrent.libs.ui.res.build.AssetBuilder;
import com.ghosttorrent.torrent.messages.AnnounceRequest;
import com.ghosttorrent.torrent.messages.AnnounceResponse;
import com.ghosttorrent.torrent.messages.GetPeersRequest;
import com.ghosttorrent.torrent.messages.GetPeersResponse;
import com.ghosttorrent.ui.GhostApplication;
import com.ghosttorrent.libs.ui.utils.inter.Application;
import unet.kad4.Kademlia;

import javax.swing.*;
import java.net.InetAddress;

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

        //new AssetBuilder();

        if(gui){
            System.setProperty("sun.java2d.opengl", "true");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Application application = new GhostApplication();
            application.launch();
        }

        Kademlia kad = new Kademlia();
        kad.registerMessage(AnnounceRequest.class);
        kad.registerMessage(AnnounceResponse.class);

        kad.registerMessage(GetPeersRequest.class);
        kad.registerMessage(GetPeersResponse.class);

        kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);
    }
}
