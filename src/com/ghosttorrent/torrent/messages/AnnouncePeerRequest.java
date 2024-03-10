package com.ghosttorrent.torrent.messages;

import unet.kad4.libs.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;

@Message(method = "announce_peer", type = MessageType.REQ_MSG)
public class AnnouncePeerRequest extends MethodMessageBase {

    public AnnouncePeerRequest(byte[] tid){
        super(tid);
    }

    @Override
    public BencodeObject encode(){
        return super.encode();
    }

    @Override
    public void decode(BencodeObject ben){
        super.decode(ben);
    }
}
