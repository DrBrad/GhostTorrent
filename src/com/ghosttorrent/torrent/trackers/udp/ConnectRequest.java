package com.ghosttorrent.torrent.trackers.udp;

public class ConnectRequest extends MessageBase {

    //BEP 15
    //                                         00 00 04 27 10 19 80

    //0x00 00 04 17 27 10 19 80
    //0x41727101980
    public static final long PROTOCOL_ID = -9216317402361102336l;
    //public static final byte[] PROTOCAL_ID = { 0x00, 0x00, 0x04, 0x17, 0x27, 0x10, 0x19, (byte) 0x80 };//{ 0x00, 0x00, 0x41, 0x72, 0x71, 0x01, (byte) 0x98, 0x00, 0x00, 0x00 };//0x41727101980;

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
        //System.arraycopy(PROTOCAL_ID, 0, buf, 0, PROTOCAL_ID.length);

        buf[0] = ((byte) PROTOCOL_ID);
        buf[1] = ((byte) (PROTOCOL_ID >>  8));
        buf[2] = ((byte) (PROTOCOL_ID >> 16));
        buf[3] = ((byte) (PROTOCOL_ID >> 24));
        buf[4] = ((byte) (PROTOCOL_ID >> 32));
        buf[5] = ((byte) (PROTOCOL_ID >> 40));
        buf[6] = ((byte) (PROTOCOL_ID >> 48));
        buf[7] = ((byte) (PROTOCOL_ID >> 56));


        //buf[8] = 0;
        buf[8] = ((byte) (0xff & action.getCode()));
        buf[9] = ((byte) (0xff & (action.getCode() >> 8)));
        buf[10] = ((byte) (0xff & (action.getCode() >> 16)));
        buf[11] = ((byte) (0xff & (action.getCode() >> 24)));
        System.arraycopy(tid, 0, buf, 12, tid.length);

        //System.out.println(new String(buf));
        return buf;
    }

    @Override
    public void decode(byte[] buf){
    }
}
