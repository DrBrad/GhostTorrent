package com.ghosttorrent.torrent.trackers.udp;

import static unet.kad4.rpc.ResponseTracker.STALLED_TIME;

public class Call {

    private MessageBase message;
    private ResponseCallback callback;
    protected long sentTime;

    public Call(MessageBase message, ResponseCallback callback){
        this.message = message;
        this.callback = callback;
        sentTime = System.currentTimeMillis();
    }

    public MessageBase getMessage(){
        return message;
    }

    public ResponseCallback getCallback(){
        return callback;
    }

    public void setSentTime(long sentTime){
        this.sentTime = sentTime;
    }

    public long getSentTime(){
        return sentTime;
    }

    public boolean isStalled(long now){
        return (now-sentTime > STALLED_TIME);
    }
}
