package com.ghosttorrent.torrent.trackers.udp;

public class ConnectRequest extends MessageBase {

    //BEP 15
    public static final byte[] PROTOCAL_ID = { 0x41, 0x72, 0x71, 0x01, (byte) 0x98, 0x00 };//0x41727101980;

    public ConnectRequest(){
        action = MessageAction.CONNECT;
    }

    public ConnectRequest(byte[] tid){
        super(tid);
        action = MessageAction.CONNECT;
    }

    @Override
    public byte[] encode(){
        byte[] buf = new byte[16];
        System.arraycopy(PROTOCAL_ID, 0, buf, 0, PROTOCAL_ID.length);
        //buf[8] = 0;
        buf[8] = ((byte) (0xff & (action.getCode() >> 24)));
        buf[9] = ((byte) (0xff & (action.getCode() >> 16)));
        buf[10] = ((byte) (0xff & (action.getCode() >> 8)));
        buf[11] = ((byte) (0xff & action.getCode()));
        System.arraycopy(tid, 0, buf, 12, tid.length);

        return buf;
    }

    @Override
    public void decode(byte[] buf){
    }
}
