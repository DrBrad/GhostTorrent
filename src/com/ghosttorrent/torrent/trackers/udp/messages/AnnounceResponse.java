package com.ghosttorrent.torrent.trackers.udp.messages;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageAction;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

public class AnnounceResponse extends MessageBase {

    public AnnounceResponse(byte[] tid){
        super(tid);
        action = MessageAction.ANNOUNCE;
    }

    @Override
    public byte[] encode(){
        return new byte[0];
    }

    @Override
    public void decode(byte[] buf){

    }
}
