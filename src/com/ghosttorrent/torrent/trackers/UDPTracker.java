package com.ghosttorrent.torrent.trackers;

import java.io.IOException;
import java.net.*;

public class UDPTracker {

    //BEP 15
    public static final byte[] PROTOCAL_ID = { 0x41, 0x72, 0x71, 0x01, (byte) 0x98, 0x00 };//0x41727101980;
    //ACTION_ID
    //TRANSACTION_ID

    private URI uri;
    private DatagramSocket socket;

    public UDPTracker(String link)throws URISyntaxException, IOException {
        uri = new URI(link);
        socket = new DatagramSocket(8080);
    }

    public void connect(){

    }

    public void announce()throws SocketException, UnknownHostException {

        byte[] buf = null;

    }

    private void send(byte[] buf)throws IOException {
        socket.send(new DatagramPacket(buf, 0, buf.length, InetAddress.getByName(uri.getHost()), uri.getPort()));
    }
}
