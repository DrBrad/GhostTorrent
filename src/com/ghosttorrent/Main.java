package com.ghosttorrent;

import com.ghosttorrent.torrent.messages.*;
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
        kad.registerMessage(AnnouncePeerRequest.class);
        kad.registerMessage(AnnouncePeerResponse.class);

        kad.registerMessage(GetRequest.class);
        kad.registerMessage(GetResponse.class);

        kad.registerMessage(PutRequest.class);
        kad.registerMessage(PutResponse.class);

        kad.registerMessage(GetPeersRequest.class);
        kad.registerMessage(GetPeersResponse.class);

        kad.registerMessage(SampleHashRequest.class);
        kad.registerMessage(SampleHashResponse.class);

        kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);

        while(true){
            Thread.sleep(10000);
            System.out.println(kad.getRoutingTable().getDerivedUID()+"  "+kad.getRoutingTable().getConsensusExternalAddress().getHostAddress());
        }
    }
}
