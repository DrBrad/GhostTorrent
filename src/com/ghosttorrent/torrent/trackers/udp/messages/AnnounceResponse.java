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
    public void decode(byte[] buf, int off, int len){
        interval = (buf[off+8] & 0xff) |
                ((buf[off+9] & 0xff) << 8) |
                ((buf[off+10] & 0xff) << 16) |
                (((buf[off+11] & 0xff) << 24));

        leachers = (buf[off+12] & 0xff) |
                ((buf[off+13] & 0xff) << 8) |
                ((buf[off+14] & 0xff) << 16) |
                (((buf[off+15] & 0xff) << 24));

        seeders = (buf[off+16] & 0xff) |
                ((buf[off+17] & 0xff) << 8) |
                ((buf[off+18] & 0xff) << 16) |
                (((buf[off+19] & 0xff) << 24));

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
