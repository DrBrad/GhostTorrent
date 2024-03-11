package com.ghosttorrent.torrent.messages;

import unet.kad4.libs.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;

@Message(method = "get_peers", type = MessageType.REQ_MSG)
public class GetPeersRequest extends MethodMessageBase {

    private byte[] infoHash;

    public GetPeersRequest(byte[] tid){
        super(tid);
    }

    @Override
    public BencodeObject encode(){
        BencodeObject ben =  super.encode();
        ben.getBencodeObject(type.innerKey()).put("info_hash", infoHash);
        return ben;
    }

    @Override
    public void decode(BencodeObject ben){
        super.decode(ben);
        if(!ben.getBencodeObject(type.innerKey()).containsKey("info_hash")){
            System.out.println("MISSING INFO_HASH");
        }

        infoHash = ben.getBencodeObject(type.innerKey()).getBytes("info_hash");
    }
}
