package unet.torrent.messages;

import unet.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;
import unet.kad4.utils.Node;
import unet.kad4.utils.net.AddressType;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.util.ArrayList;
import java.util.List;

import static unet.kad4.utils.NodeUtils.packNodes;
import static unet.kad4.utils.NodeUtils.unpackNodes;

@Message(method = "get_peers", type = MessageType.RSP_MSG)
public class GetPeersResponse extends MethodMessageBase {

    public static final int NODE_CAP = 20;

    private byte[] token;
    private List<String> peers;
    private List<Node> nodes;

    /*
    arguments:  {"id" : "<querying nodes id>", "info_hash" : "<20-byte infohash of target torrent>"}
    response: {"id" : "<queried nodes id>", "token" :"<opaque write token>", "values" : ["<peer 1 info string>", "<peer 2 info string>"]}
    or: {"id" : "<queried nodes id>", "token" :"<opaque write token>", "nodes" : "<compact node info>"}


    Example Packets:
    get_peers Query = {"t":"aa", "y":"q", "q":"get_peers", "a": {"id":"abcdefghij0123456789", "info_hash":"mnopqrstuvwxyz123456"}}
    bencoded = d1:ad2:id20:abcdefghij01234567899:info_hash20:mnopqrstuvwxyz123456e1:q9:get_peers1:t2:aa1:y1:qe

    Response with peers = {"t":"aa", "y":"r", "r": {"id":"abcdefghij0123456789", "token":"aoeusnth", "values": ["axje.u", "idhtnm"]}}
    bencoded = d1:rd2:id20:abcdefghij01234567895:token8:aoeusnth6:valuesl6:axje.u6:idhtnmee1:t2:aa1:y1:re

    Response with closest nodes = {"t":"aa", "y":"r", "r": {"id":"abcdefghij0123456789", "token":"aoeusnth", "nodes": "def456..."}}
    bencoded = d1:rd2:id20:abcdefghij01234567895:nodes9:def456...5:token8:aoeusnthe1:t2:aa1:y1:re
    */

    public GetPeersResponse(byte[] tid){
        super(tid);
        peers = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    @Override
    public BencodeObject encode(){
        BencodeObject ben =  super.encode();
        ben.getBencodeObject(type.innerKey()).put("token", token);

        if(peers.isEmpty()){
            if(nodes.isEmpty()){
                return ben;
            }

            List<Node> nodes = getIPv4Nodes();
            if(!nodes.isEmpty()){
                ben.getBencodeObject(type.innerKey()).put("nodes", packNodes(nodes, AddressType.IPv4));
            }

            nodes = getIPv6Nodes();
            if(!nodes.isEmpty()){
                ben.getBencodeObject(type.innerKey()).put("nodes6", packNodes(nodes, AddressType.IPv6));
            }

            return ben;
        }

        //WHAT ABOUT PEERS...?

        return ben;
    }

    @Override
    public void decode(BencodeObject ben){
        super.decode(ben);

        if(!ben.getBencodeObject(type.innerKey()).containsKey("token")){
            //System.out.println("MISSING TOKEN");
            return;
        }

        token = ben.getBencodeObject(type.innerKey()).getBytes("token");

        if(!ben.containsKey("values")){
            if(!ben.getBencodeObject(type.innerKey()).containsKey("nodes") &&
                    !ben.getBencodeObject(type.innerKey()).containsKey("nodes6")){
                //throw new MessageException("Response to "+FIND_NODE+" did not contain 'node' or 'node6'", ErrorMessage.ErrorType.PROTOCOL);
                return;
            }

            if(ben.getBencodeObject(type.innerKey()).containsKey("nodes")){
                nodes.addAll(unpackNodes(ben.getBencodeObject(type.innerKey()).getBytes("nodes"), AddressType.IPv4));
            }

            if(ben.getBencodeObject(type.innerKey()).containsKey("nodes6")){
                nodes.addAll(unpackNodes(ben.getBencodeObject(type.innerKey()).getBytes("nodes6"), AddressType.IPv6));
            }
            return;
        }

        System.out.println(ben);
    }

    public void setToken(byte[] token){
        this.token = token;
    }

    public byte[] getToken(){
        return token;
    }

    public void addPeer(String peer){

    }

    public List<String> getPeers(){
        return peers;
    }


    public void addNode(Node node){
        nodes.add(node);
    }

    public Node getNode(int i){
        return nodes.get(i);
    }

    public void removeNode(Node node){
        nodes.remove(node);
    }

    public boolean containsNode(Node node){
        return nodes.contains(node);
    }

    public boolean hasNodes(){
        return !nodes.isEmpty();
    }

    public void addNodes(List<Node> nodes){
        if(nodes.size()+this.nodes.size() > NODE_CAP){
            throw new IllegalArgumentException("Adding nodes would exceed Node Cap of "+NODE_CAP);
        }

        this.nodes.addAll(nodes);
    }

    public List<Node> getAllNodes(){
        return nodes;
    }

    public List<Node> getIPv4Nodes(){
        List<Node> r = new ArrayList<>();

        for(Node node : nodes){
            if(node.getHostAddress() instanceof Inet4Address){
                r.add(node);
            }
        }
        return r;
    }

    public List<Node> getIPv6Nodes(){
        List<Node> r = new ArrayList<>();

        for(Node node : nodes){
            if(node.getHostAddress() instanceof Inet6Address){
                r.add(node);
            }
        }
        return r;
    }
}
