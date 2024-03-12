package com.ghosttorrent.torrent.trackers;

import com.ghosttorrent.torrent.messages.AnnouncePeerRequest;
import com.ghosttorrent.torrent.messages.GetPeersRequest;
import unet.kad4.routing.kb.KBucket;
import unet.kad4.rpc.events.ResponseEvent;
import unet.kad4.rpc.events.inter.ResponseCallback;
import unet.kad4.utils.Node;
import unet.kad4.utils.UID;

import java.io.IOException;
import java.util.List;

import static com.ghosttorrent.Main.kad;

public class DHTTracker {

    public DHTTracker(byte[] infoHash){
        GetPeersRequest request = new GetPeersRequest();

        List<Node> nodes = kad.getRoutingTable().findClosest(new UID(infoHash), KBucket.MAX_BUCKET_SIZE);

        for(Node node : nodes){
            request.setDestination(node.getAddress());
            request.setInfoHash(infoHash);

            try{

                kad.getServer().send(request, new ResponseCallback(){
                    @Override
                    public void onResponse(ResponseEvent event){
                        System.out.println("RESPONSE - GET_PEERS");
                    }
                });

            }catch(IOException e){
                e.printStackTrace();
            }
        }


        //THEN ANNOUNCE...
    }
}
