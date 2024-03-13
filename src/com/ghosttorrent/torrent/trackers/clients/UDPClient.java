package com.ghosttorrent.torrent.trackers.clients;

import com.ghosttorrent.torrent.trackers.udp.*;
import unet.kad4.utils.ByteWrapper;

import java.io.IOException;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class UDPClient {

    //BEP 15
    public static final int TID_LENGTH = 4;

    //ACTION_ID
    //TRANSACTION_ID

    //private InetSocketAddress address;
    private SecureRandom random;
    private ResponseTracker tracker;
    private DatagramSocket socket;

    public UDPClient(){
        //URI uri = new URI(link);
        //address = new InetSocketAddress(InetAddress.getByName(uri.getHost()), uri.getPort());
        tracker = new ResponseTracker();

        try{
            random = SecureRandom.getInstance("SHA1PRNG");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public void start(int port)throws SocketException {
        if(isRunning()){
            throw new IllegalArgumentException("Server has already started.");
        }

        socket = new DatagramSocket(port);

        System.out.println("Started UDP Tracker Client");

        new Thread(new Runnable(){
            @Override
            public void run(){
                while(!socket.isClosed()){
                    try{
                        DatagramPacket packet = new DatagramPacket(new byte[65535], 65535);
                        socket.receive(packet);

                        System.out.println("RECEIVED- 1");

                        if(packet != null){
                            receive(packet);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public boolean isRunning(){
        return (socket != null && !socket.isClosed());
    }

    public int getPort(){
        return (socket != null) ? socket.getLocalPort() : 0;
    }

    private void receive(DatagramPacket packet){
        byte[] buf = packet.getData();
        System.out.println("RECEIVED");

        /*
        Offset  Size            Name            Value
        0       32-bit integer  action          0 // connect
        4       32-bit integer  transaction_id
        */
        MessageAction action = MessageAction.fromCode((((buf[0] & 0xff) << 24) |
                ((buf[1] & 0xff) << 16) |
                ((buf[2] & 0xff) << 8) |
                (buf[3] & 0xff)));

        byte[] tid = new byte[4];
        System.arraycopy(buf, 4, tid, 0, tid.length);

        if(!tracker.contains(new ByteWrapper(tid))){
            return;
        }

        MessageBase response;

        switch(action){
            case CONNECT:
                response = new ConnectResponse(tid);
                break;

            case ANNOUNCE:
                response = null;
                break;

            case SCRAPE:
                response = null;
                break;

            case ERROR:
                response = null;
                break;

            default:
                return;
        }

        response.decode(buf);
        response.setOrigin(packet.getAddress(), packet.getPort());
        tracker.get(new ByteWrapper(tid)).getCallback().onResponse(response);
    }

    /*
    public void connect(InetSocketAddress address)throws IOException {
        ConnectRequest request = new ConnectRequest();
        request.setDestination(address);
    }

    public void announce()throws SocketException, UnknownHostException {

        byte[] buf = null;

    }
    */

    public void send(MessageBase message, ResponseCallback callback)throws IOException {
        byte[] tid = generateTransactionID();
        message.setTransactionID(tid);
        tracker.add(new ByteWrapper(tid), new Call(message, callback));

        byte[] data = message.encode();
        socket.send(new DatagramPacket(data, 0, data.length, message.getDestination()));
    }

    private byte[] generateTransactionID(){
        byte[] tid = new byte[TID_LENGTH];
        random.nextBytes(tid);
        return tid;
    }
}