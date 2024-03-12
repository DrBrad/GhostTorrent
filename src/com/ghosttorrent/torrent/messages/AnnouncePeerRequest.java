package com.ghosttorrent.torrent.messages;

import unet.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;

@Message(method = "announce_peer", type = MessageType.REQ_MSG)
public class AnnouncePeerRequest extends MethodMessageBase {

    private int impliedPort, port; //IMPLIED PORT IS 0 | 1 ...?
    private byte[] infoHash, token;

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
    }
}
