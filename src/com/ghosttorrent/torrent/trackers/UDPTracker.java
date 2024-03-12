package com.ghosttorrent.torrent.trackers;

import java.io.IOException;
import java.net.*;

public class UDPTracker {

    //BEP 15

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
