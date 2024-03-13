package com.ghosttorrent.ui.listeners;

import com.ghosttorrent.torrent.Torrent;
import com.ghosttorrent.torrent.trackers.DHTTracker;
import com.ghosttorrent.torrent.trackers.clients.UDPClient;
import com.ghosttorrent.torrent.trackers.servers.UDPTracker;
import com.ghosttorrent.torrent.trackers.udp.ConnectRequest;
import com.ghosttorrent.torrent.trackers.udp.ConnectResponse;
import com.ghosttorrent.torrent.trackers.udp.MessageBase;
import com.ghosttorrent.torrent.trackers.udp.ResponseCallback;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class OpenTorrentListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e){
        FileDialog dialog = new FileDialog((Frame) null);
        //dialog.setFilenameFilter(new FilenameFilter().accept(null, ""));
        dialog.setMultipleMode(true);
        dialog.setVisible(true);

        if(dialog.getDirectory() == null ||
                dialog.getFile() == null){
            return;
        }

        File file = new File(dialog.getDirectory()+"/"+dialog.getFile());
        Torrent torrent = new Torrent(file);
        System.out.println(torrent.getAnnounce());
        System.out.println(torrent.getComment());
        System.out.println(torrent.getCreatedBy());
        System.out.println(torrent.getFiles().get(0).getPath().get(0));

        StringBuilder builder = new StringBuilder();
        for(byte b : torrent.getInfoHash()){
            builder.append(String.format("%02x", b));
        }

        System.out.println(builder);

        //new DHTTracker(torrent.getInfoHash());

        UDPClient client = new UDPClient();
        try{
            client.start(6969);

            for(String announce : torrent.getAnnounceList()){
                try{
                    URI uri = new URI(announce);
                    if(!uri.getScheme().equals("udp")){
                        continue;
                    }

                    System.out.println("UDP SENDING:  "+InetAddress.getByName(uri.getHost()).getHostAddress()+"  "+uri.getPort());

                    ConnectRequest request = new ConnectRequest();
                    request.setDestination(InetAddress.getByName(uri.getHost()), uri.getPort());
                    client.send(request, new ResponseCallback(){
                        @Override
                        public void onResponse(MessageBase message){
                            ConnectResponse response = (ConnectResponse) message;
                            System.out.println(new String(response.getConnectionID()));
                        }
                    });
                }catch(URISyntaxException ex){
                    ex.printStackTrace();
                }
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
