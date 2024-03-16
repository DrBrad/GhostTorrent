package com.ghosttorrent.torrent.trackers.udp.messages;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageAction;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

public class ErrorResponse extends MessageBase {

    private String message;

    public ErrorResponse(byte[] tid){
        this.tid = tid;
        action = MessageAction.ERROR;
    }

    @Override
    public byte[] encode(){
        return new byte[0];
    }

    @Override
    public void decode(byte[] buf, int off, int len){
        message = new String(buf, off+8, len-9);
        System.out.println(message);
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
