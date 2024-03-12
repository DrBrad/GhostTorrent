package com.ghosttorrent.torrent.messages;

import unet.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;
import unet.kad4.utils.Node;

import java.util.ArrayList;
import java.util.List;

@Message(method = "get_peers", type = MessageType.RSP_MSG)
public class GetPeersResponse extends MethodMessageBase {

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
        return ben;
    }

    @Override
    public void decode(BencodeObject ben){
        super.decode(ben);
        if(!ben.getBencodeObject(type.innerKey()).containsKey("token")){
            System.out.println("MISSING INFO_HASH");
        }

        token = ben.getBencodeObject(type.innerKey()).getBytes("token");
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

    public List<Node> getNodes(){
        return nodes;
    }
}
