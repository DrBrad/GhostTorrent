package com.ghosttorrent.ui.listeners;

import unet.torrent.Torrent;
import unet.torrent.trackers.clients.UDPClient;
import unet.torrent.trackers.udp.messages.AnnounceRequest;
import unet.torrent.trackers.udp.messages.AnnounceResponse;
import unet.torrent.trackers.udp.messages.ConnectRequest;
import unet.torrent.trackers.udp.messages.ConnectResponse;
import unet.torrent.trackers.udp.messages.inter.AnnounceEvent;
import unet.torrent.trackers.udp.messages.inter.MessageBase;
import unet.torrent.trackers.udp.ResponseCallback;

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
        System.out.println(torrent.getInfo().getFiles().get(0).getPath().get(0));

        StringBuilder builder = new StringBuilder();
        for(byte b : torrent.getInfo().getHash()){
            builder.append(String.format("%02x", b));
        }

        /*
        for(byte[] piece : torrent.getInfo().getPieces()){
            System.out.println("PIECE: "+bytesToHex(piece));
        }
        */

        System.out.println(builder);

        //WE NEED LEFT TO CALC TO: 1880132108
        //WE NEED NUM-WANT TO BE 80


        return;

        /*

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
                            request1.setInfoHash(torrent.getInfo().getHash()); //9e8cb640823822be312c1278089c96cafacc8627
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
        */

    }

    private byte[] stringToHex(String s){
        byte[] b = new byte[s.length()/2];
        for(int i = 0; i < b.length; i += 2){
            b[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+Character.digit(s.charAt(i+1), 16));
        }

        return b;
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
