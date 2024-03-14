package com.ghosttorrent;

import com.ghosttorrent.torrent.Magnet;
import com.ghosttorrent.torrent.messages.*;
import com.ghosttorrent.torrent.trackers.clients.UDPClient;
import com.ghosttorrent.torrent.trackers.udp.ConnectRequest;
import com.ghosttorrent.torrent.trackers.udp.ConnectResponse;
import com.ghosttorrent.torrent.trackers.udp.MessageBase;
import com.ghosttorrent.torrent.trackers.udp.ResponseCallback;
import com.ghosttorrent.ui.GhostApplication;
import com.ghosttorrent.libs.ui.utils.inter.Application;
import unet.kad4.Kademlia;

import javax.swing.*;
import java.net.InetAddress;
import java.net.URI;

public class Main {

    //HONESTLY WE COULD JUST MAKE OUR OWN COMPONENTS AND FILL WITH REFLECT - TODO
    //TORRENT I/O STREAM SHOULD BE MADE...

    public static Kademlia kad;

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

        kad = new Kademlia();
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
        byte[] a = { 0x41, 0x72, 0x71, 0x01, (byte) 0x98, (byte) 0x80 };

        long v = 0;

        for (byte b : a) {
            v = (v << 8) | (b & 0xFF);
        }*/

        long v = -9216317402361102336l;

        /*
        byte[] bigEndianBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bigEndianBytes[i] = (byte) ((v >> (7 - i) * 8) & 0xFF);
        }

        byte[] flippedBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            flippedBytes[i] = bigEndianBytes[7 - i];
        }*/

        byte[] bigEndianBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bigEndianBytes[i] = (byte) (v >> ((7 - i) * 8));
        }

        // Output the bytes

        byte[] buf = new byte[8];
        buf[7] = ((byte) (0xff & (v >> 56)));
        buf[6] = ((byte) (0xff & (v >> 48)));
        buf[5] = ((byte) (0xff & (v >> 40)));
        buf[4] = ((byte) (0xff & (v >> 32)));
        buf[3] = ((byte) (0xff & (v >> 24)));
        buf[2] = ((byte) (0xff & (v >> 16)));
        buf[1] = ((byte) (0xff & (v >>  8)));
        buf[0] = ((byte) (0xff & v));

        System.out.println(v);
        //0x00 00 04 17 27 10 19 80
        //  00 00 41 72 71 01 98 80
        System.out.println(bytesToHex(bigEndianBytes));
        System.out.println(bytesToHex(buf));

        v = 0;
        for (byte b : bigEndianBytes) {
            v = (v << 8) | (b & 0xFF);
        }


        System.out.println(v);

        /*
        kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);

        while(true){
            Thread.sleep(10000);
            System.out.println(kad.getRoutingTable().getDerivedUID()+"  "+kad.getRoutingTable().getConsensusExternalAddress().getHostAddress()+"  "+kad.getRoutingTable().getAllNodes().size());
        }
        */

        /*
        String magnet = "magnet:?xt=urn:btih:4f836d43e43e8682ff6d2e197e32d7e4f4fb6af4&dn=Ubuntu+Linux+20.04+LTS+Desktop+64-bit&tr=http%3A%2F%2Ftorrent.ubuntu.com%3A6969%2Fannounce";
        new Magnet(magnet);

        System.out.println();

        magnet = "magnet:?xt=urn:btih:1061709FB638C2CE8728B185846EC5974BEE370F&dn=Wish+%282023%29+%5B1080p%5D+%5BYTS.MX%5D&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337%2Fannounce&tr=udp%3A%2F%2Fopen.tracker.cl%3A1337%2Fannounce&tr=udp%3A%2F%2Fp4p.arenabg.com%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.torrent.eu.org%3A451%2Fannounce&tr=udp%3A%2F%2Ftracker.dler.org%3A6969%2Fannounce&tr=udp%3A%2F%2Fopen.stealth.si%3A80%2Fannounce&tr=udp%3A%2F%2Fipv4.tracker.harry.lu%3A80%2Fannounce&tr=https%3A%2F%2Fopentracker.i2p.rocks%3A443%2Fannounce";
        new Magnet(magnet);
        */
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
