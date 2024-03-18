package com.ghosttorrent.torrent.trackers.udp.messages;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageAction;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

public class ScrapeResponse extends MessageBase {

    public ScrapeResponse(byte[] tid){
        super(tid);
        action = MessageAction.SCRAPE;
    }

    @Override
    public byte[] encode(){
        return new byte[0];
    }

    @Override
    public void decode(byte[] buf, int off, int len){

    }
}
