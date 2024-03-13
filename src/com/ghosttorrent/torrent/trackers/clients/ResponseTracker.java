package com.ghosttorrent.torrent.trackers.clients;

import com.ghosttorrent.torrent.trackers.udp.Call;
import unet.kad4.utils.ByteWrapper;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ResponseTracker {

    public static final int MAX_ACTIVE_CALLS = 512;

    //public static final long STALLED_TIME = 60000;
    private final UDPClient client;
    private final LinkedHashMap<ByteWrapper, Call> calls;
    //private final ConcurrentHashMap<ByteWrapper, RequestEvent> calls;
    //private final ConcurrentLinkedQueue<ByteWrapper> callsOrder;

    public ResponseTracker(UDPClient client){
        this.client = client;
        calls = new LinkedHashMap<>(MAX_ACTIVE_CALLS);
        //calls = new ConcurrentHashMap<>(MAX_ACTIVE_CALLS);
        //callsOrder = new ConcurrentLinkedQueue<>();
    }

    public synchronized void add(ByteWrapper tid, Call call){
        calls.put(tid, call);
    }

    public synchronized Call get(ByteWrapper tid){
        return calls.get(tid);
    }

    public synchronized boolean contains(ByteWrapper tid){
        return calls.containsKey(tid);
    }

    public synchronized void remove(ByteWrapper tid){
        calls.remove(tid);
    }

    public synchronized Call poll(ByteWrapper tid){
        Call call = calls.get(tid);
        calls.remove(tid);
        return call;
    }

    public synchronized void removeStalled(){
        long now = System.currentTimeMillis();

        List<ByteWrapper> stalled = new ArrayList<>();

        for(ByteWrapper tid : calls.keySet()){
            if(!calls.get(tid).isStalled(now)){
                break;
            }

            stalled.add(tid);
        }

        for(ByteWrapper tid : stalled){
            Call call = calls.get(tid);

            if(call.hasMaxedAttempts()){
                calls.remove(tid);
                System.err.println("STALLED");
                return;
            }

            try{
                client.retry(call);
                System.out.println("RETRYING "+call.getAttempts());
            }catch(IOException e){
                e.printStackTrace();
            }


            /*
            System.err.println("STALLED "+((call.hasNode()) ? call.getNode() : ""));

            if(call.hasResponseCallback()){
                StalledEvent event = new StalledEvent(call.getMessage());
                event.setSentTime(call.getSentTime());

                if(call.hasNode()){
                    event.setNode(call.getNode());
                }

                call.getResponseCallback().onStalled(event);
            }
            */
        }
    }
}
