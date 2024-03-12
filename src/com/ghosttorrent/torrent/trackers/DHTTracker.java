package com.ghosttorrent.torrent.trackers;

import com.ghosttorrent.torrent.messages.AnnouncePeerRequest;
import com.ghosttorrent.torrent.messages.GetPeersRequest;
import com.ghosttorrent.torrent.messages.GetPeersResponse;
import unet.kad4.routing.kb.KBucket;
import unet.kad4.rpc.events.ResponseEvent;
import unet.kad4.rpc.events.inter.ResponseCallback;
import unet.kad4.utils.Node;
import unet.kad4.utils.UID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ghosttorrent.Main.kad;

public class DHTTracker {

    private byte[] infoHash;

    //WHY IS THIS CAUSING MULTIPLE RESTARTS...
    //WHY IS THE SERVER UNREACHABLE...
    private List<Node> connected;

    public DHTTracker(byte[] infoHash){
        this.infoHash = infoHash;
        connected = new ArrayList<>();

        connected = kad.getRoutingTable().findClosest(new UID(infoHash), KBucket.MAX_BUCKET_SIZE);
        getPeers(connected, 0);


        //THEN ANNOUNCE...
    }

    public void getPeers(List<Node> nodes, int attempts){
        for(Node node : nodes){
            GetPeersRequest request = new GetPeersRequest();
            request.setDestination(node.getAddress());
            request.setInfoHash(infoHash);

            try{
                kad.getServer().send(request, node, new ResponseCallback(){
                    @Override
                    public void onResponse(ResponseEvent event){
                        kad.getRoutingTable().insert(node);
                        System.out.println("GET_PEERS "+node);
                        GetPeersResponse response = (GetPeersResponse) event.getMessage();
                        if(response.getToken() == null){
                            return;
                        }

                        if(response.hasNodes()){
                            if(attempts > 2){
                                return;
                            }

                            List<Node> nodes = response.getAllNodes();

                            for(int i = nodes.size()-1; i > -1; i--){
                                nodes.remove(node);
                            }

                            connected.addAll(nodes);

                            getPeers(nodes, attempts+1);
                        }
                    }
                });

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
