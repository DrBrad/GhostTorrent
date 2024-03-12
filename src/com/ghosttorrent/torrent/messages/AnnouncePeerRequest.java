package com.ghosttorrent.torrent.messages;

import unet.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;

@Message(method = "announce_peer", type = MessageType.REQ_MSG)
public class AnnouncePeerRequest extends MethodMessageBase {

    private int impliedPort = 1, port; //IMPLIED PORT IS 0 | 1 ...?
    private byte[] infoHash, token;

    public AnnouncePeerRequest(){
    }

    public AnnouncePeerRequest(byte[] tid){
        super(tid);
    }

    @Override
    public BencodeObject encode(){
        BencodeObject ben = super.encode();

        ben.getBencodeObject(type.innerKey()).put("implied_port", impliedPort);
        ben.getBencodeObject(type.innerKey()).put("info_hash", infoHash);
        ben.getBencodeObject(type.innerKey()).put("port", port);
        ben.getBencodeObject(type.innerKey()).put("token", token);

        return ben;
    }

    @Override
    public void decode(BencodeObject ben){
        super.decode(ben);

        if(!ben.getBencodeObject(type.innerKey()).containsKey("implied_port") ||
                !ben.getBencodeObject(type.innerKey()).containsKey("info_hash") ||
                !ben.getBencodeObject(type.innerKey()).containsKey("implied_port") ||
                !ben.getBencodeObject(type.innerKey()).containsKey("token")){
            System.out.println("MISSING TOKEN");
        }

        impliedPort = ben.getBencodeObject(type.innerKey()).getInteger("implied_port");
        infoHash = ben.getBencodeObject(type.innerKey()).getBytes("info_hash");
        port = ben.getBencodeObject(type.innerKey()).getInteger("port");
        token = ben.getBencodeObject(type.innerKey()).getBytes("token");
    }

    public boolean isImpliedPort(){
        return (impliedPort == 1);
    }

    public void setImpliedPort(boolean impliedPort){
        this.impliedPort = (impliedPort) ? 1 : 0;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public byte[] getInfoHash(){
        return infoHash;
    }

    public void setInfoHash(byte[] infoHash){
        this.infoHash = infoHash;
    }

    public byte[] getToken(){
        return token;
    }

    public void setToken(byte[] token){
        this.token = token;
    }
}
