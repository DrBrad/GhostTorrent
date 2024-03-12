package com.ghosttorrent;

import com.ghosttorrent.torrent.Magnet;
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
        //BEP 5
        kad.registerMessage(AnnouncePeerRequest.class);
        kad.registerMessage(AnnouncePeerResponse.class);

        //BEP 5
        kad.registerMessage(GetRequest.class);
        kad.registerMessage(GetResponse.class);

        //BEP 5
        kad.registerMessage(PutRequest.class);
        kad.registerMessage(PutResponse.class);

        //BEP 5
        kad.registerMessage(GetPeersRequest.class);
        kad.registerMessage(GetPeersResponse.class);

        //BEP 51
        kad.registerMessage(SampleHashRequest.class);
        kad.registerMessage(SampleHashResponse.class);

        /*
        kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);

        while(true){
            Thread.sleep(10000);
            System.out.println(kad.getRoutingTable().getDerivedUID()+"  "+kad.getRoutingTable().getConsensusExternalAddress().getHostAddress());
        }

        /*
        String magnet = "magnet:?xt=urn:btih:4f836d43e43e8682ff6d2e197e32d7e4f4fb6af4&dn=Ubuntu+Linux+20.04+LTS+Desktop+64-bit&tr=http%3A%2F%2Ftorrent.ubuntu.com%3A6969%2Fannounce";
        new Magnet(magnet);

        System.out.println();

        magnet = "magnet:?xt=urn:btih:1061709FB638C2CE8728B185846EC5974BEE370F&dn=Wish+%282023%29+%5B1080p%5D+%5BYTS.MX%5D&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337%2Fannounce&tr=udp%3A%2F%2Fopen.tracker.cl%3A1337%2Fannounce&tr=udp%3A%2F%2Fp4p.arenabg.com%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.torrent.eu.org%3A451%2Fannounce&tr=udp%3A%2F%2Ftracker.dler.org%3A6969%2Fannounce&tr=udp%3A%2F%2Fopen.stealth.si%3A80%2Fannounce&tr=udp%3A%2F%2Fipv4.tracker.harry.lu%3A80%2Fannounce&tr=https%3A%2F%2Fopentracker.i2p.rocks%3A443%2Fannounce";
        new Magnet(magnet);
        */

    }
}
