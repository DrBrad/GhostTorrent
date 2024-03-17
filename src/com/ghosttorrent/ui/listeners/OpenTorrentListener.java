package com.ghosttorrent.ui.listeners;

import com.ghosttorrent.torrent.Torrent;
import com.ghosttorrent.torrent.trackers.clients.UDPClient;
import com.ghosttorrent.torrent.trackers.udp.messages.AnnounceRequest;
import com.ghosttorrent.torrent.trackers.udp.messages.AnnounceResponse;
import com.ghosttorrent.torrent.trackers.udp.messages.ConnectRequest;
import com.ghosttorrent.torrent.trackers.udp.messages.ConnectResponse;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.AnnounceEvent;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;
import com.ghosttorrent.torrent.trackers.udp.ResponseCallback;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

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
                            System.out.println("RESPONSE: "+response.getConnectionID());

                            //DO THE REQUESTS BETTER...?
                            AnnounceRequest request1 = new AnnounceRequest();
                            request1.setDestination(request.getDestination());
                            request1.setConnectionID(response.getConnectionID());
                            request1.setEvent(AnnounceEvent.STARTED);
                            request1.setInfoHash(torrent.getInfoHash()); //9e8cb640823822be312c1278089c96cafacc8627
                            request1.setPeerID(stringToHex("2d5452333030302d326f71727270786231303232"));
                            request1.setDownloaded(0);
                            request1.setLeft(1880132108); //MUST CALC THE AMMOUNT WE NEED...
                            request1.setUploaded(0);
                            request1.setNumWant(80);
                            request1.setKey(330182370);
                            request1.setPort(8080); //TCP PORT

                            try{
                                client.send(request1, new ResponseCallback(){
                                    @Override
                                    public void onResponse(MessageBase message){
                                        AnnounceResponse response = (AnnounceResponse) message;

                                        System.out.println(response.getInterval()+"  "+response.getLeachers()+"  "+response.getSeeders());
                                    }
                                });
                            }catch(IOException ex){
                                ex.printStackTrace();
                            }

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

    private byte[] stringToHex(String s){
        byte[] b = new byte[s.length()/2];
        for(int i = 0; i < b.length; i += 2){
            b[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+Character.digit(s.charAt(i+1), 16));
        }

        return b;
    }
}
