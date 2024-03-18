package unet.torrent.trackers.udp;

import unet.torrent.trackers.udp.messages.inter.MessageBase;

public class Call {

    private MessageBase message;
    private ResponseCallback callback;
    protected long sentTime;
    protected int attempts = 0;

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
        return (now-sentTime > 15*Math.pow(2, attempts)*1000);//(30^attempts)*1000);
    }

    public boolean hasMaxedAttempts(){
        return (attempts > 7);
    }

    public void setAttempted(){
        attempts++;
    }

    public int getAttempts(){
        return attempts;
    }
}
