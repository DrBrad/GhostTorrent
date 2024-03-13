package com.ghosttorrent.torrent.trackers.clients;

import com.ghosttorrent.torrent.trackers.udp.*;
import unet.kad4.utils.ByteWrapper;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class UDPClient {

    //BEP 15
    public static final byte[] PROTOCAL_ID = { 0x41, 0x72, 0x71, 0x01, (byte) 0x98, 0x00 };//0x41727101980;
    private Map<ByteWrapper, Call> calls;
    //ACTION_ID
    //TRANSACTION_ID

    //private InetSocketAddress address;
    private DatagramSocket socket;

    public UDPClient(String link)throws URISyntaxException, IOException {
        //URI uri = new URI(link);
        //address = new InetSocketAddress(InetAddress.getByName(uri.getHost()), uri.getPort());
        calls = new HashMap();

        socket = new DatagramSocket(6969);

        new Thread(new Runnable(){
            @Override
            public void run(){
                while(!socket.isClosed()){
                    try{
                        DatagramPacket packet = new DatagramPacket(new byte[65535], 65535);
                        socket.receive(packet);

                        if(packet != null){
                            receive(packet);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        /*
        AWAIT RESPONSES...
        */
    }

    private void receive(DatagramPacket packet){
        byte[] buf = packet.getData();

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

        if(!calls.containsKey(new ByteWrapper(tid))){
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

        response.setOrigin(packet.getAddress(), packet.getPort());

        calls.get(new ByteWrapper(tid)).getCallback().onResponse(response);
    }

    public void connect(InetSocketAddress address)throws IOException {
        ConnectRequest request = new ConnectRequest();
        request.setDestination(address);
        request.setTransactionID(/* GENERATE RANDOM TID */);
    }

    public void announce()throws SocketException, UnknownHostException {

        byte[] buf = null;

    }

    private void send(MessageBase message, ResponseCallback callback)throws IOException {
        byte[] data = message.encode();
        socket.send(new DatagramPacket(data, 0, data.length, message.getDestination()));
    }
}
