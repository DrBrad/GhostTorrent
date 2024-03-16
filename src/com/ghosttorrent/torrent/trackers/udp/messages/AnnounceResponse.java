package com.ghosttorrent.torrent.trackers.udp.messages;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageAction;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

public class AnnounceResponse extends MessageBase {

    private int interval, leachers, seeders;

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
        interval = (buf[8] & 0xff) |
                ((buf[9] & 0xff) << 8) |
                ((buf[10] & 0xff) << 16) |
                (((buf[11] & 0xff) << 24));

        leachers = (buf[12] & 0xff) |
                ((buf[13] & 0xff) << 8) |
                ((buf[14] & 0xff) << 16) |
                (((buf[15] & 0xff) << 24));

        seeders = (buf[16] & 0xff) |
                ((buf[17] & 0xff) << 8) |
                ((buf[18] & 0xff) << 16) |
                (((buf[19] & 0xff) << 24));

        //HOW DO WE KNOW IF THIS IS IPv6....?
    }

    public void setInterval(int interval){
        this.interval = interval;
    }

    public int getInterval(){
        return interval;
    }

    public void setLeachers(int leachers){
        this.leachers = leachers;
    }

    public int getLeachers(){
        return leachers;
    }

    public void setSeeders(int seeders){
        this.seeders = seeders;
    }

    public int getSeeders(){
        return seeders;
    }
}
