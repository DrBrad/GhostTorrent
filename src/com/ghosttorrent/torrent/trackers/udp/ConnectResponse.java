package com.ghosttorrent.torrent.trackers.udp;

public class ConnectResponse extends MessageBase {

    private byte[] connectionID;

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
        connectionID = new byte[8];
        System.arraycopy(buf, 8, connectionID, 0, connectionID.length);
    }

    public byte[] getConnectionID(){
        return connectionID;
    }

    public void setConnectionID(byte[] connectionID){
        this.connectionID = connectionID;
    }
}
