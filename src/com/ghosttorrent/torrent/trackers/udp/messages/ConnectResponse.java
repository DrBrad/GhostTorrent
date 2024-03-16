package com.ghosttorrent.torrent.trackers.udp.messages;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageAction;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

public class ConnectResponse extends MessageBase {

    /*
    Offset  Size            Name            Value
    0       32-bit integer  action          0 // connect
    4       32-bit integer  transaction_id
    8       64-bit integer  connection_id
    16
    */

    private long connectionID;

    public ConnectResponse(byte[] tid){
        super(tid);
        action = MessageAction.CONNECT;
    }

    @Override
    public byte[] encode(){
        return new byte[0];
    }

    @Override
    public void decode(byte[] buf){
        connectionID = (((long)(buf[0] & 0xff)) |
                ((long)(buf[1] & 0xff) <<  8) |
                ((long)(buf[2] & 0xff) << 16) |
                ((long)(buf[3] & 0xff) << 24) |
                ((long)(buf[4] & 0xff) << 32) |
                ((long)(buf[5] & 0xff) << 40) |
                ((long)(buf[6] & 0xff) << 48) |
                ((long)(buf[7] & 0xff) << 56));
    }

    public long getConnectionID(){
        return connectionID;
    }

    public void setConnectionID(long connectionID){
        this.connectionID = connectionID;
    }
}