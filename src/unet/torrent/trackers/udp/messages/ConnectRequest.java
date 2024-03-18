package unet.torrent.trackers.udp.messages;

import unet.torrent.trackers.udp.messages.inter.MessageAction;
import unet.torrent.trackers.udp.messages.inter.MessageBase;

public class ConnectRequest extends MessageBase {

    //BEP 15
    /*
    Offset  Size            Name            Value
    0       64-bit integer  protocol_id     0x41727101980 // magic constant
    8       32-bit integer  action          0 // connect
    12      32-bit integer  transaction_id
    16
    */
    //0x0000   00 00 04 17 27 10 19 80
    public static final byte[] PROTOCOL_ID = { 0x00, 0x00, 0x04, 0x17, 0x27, 0x10, 0x19, (byte) 0x80 };//-9216317402361102336l;

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

        /*
        buf[0] = ((byte) PROTOCOL_ID);
        buf[1] = ((byte) (PROTOCOL_ID >>  8));
        buf[2] = ((byte) (PROTOCOL_ID >> 16));
        buf[3] = ((byte) (PROTOCOL_ID >> 24));
        buf[4] = ((byte) (PROTOCOL_ID >> 32));
        buf[5] = ((byte) (PROTOCOL_ID >> 40));
        buf[6] = ((byte) (PROTOCOL_ID >> 48));
        buf[7] = ((byte) (PROTOCOL_ID >> 56));
        */
        System.arraycopy(PROTOCOL_ID, 0, buf, 0, PROTOCOL_ID.length);

        buf[8] = ((byte) action.getCode());
        buf[9] = ((byte) (action.getCode() >> 8));
        buf[10] = ((byte) (action.getCode() >> 16));
        buf[11] = ((byte) (action.getCode() >> 24));
        System.arraycopy(tid, 0, buf, 12, tid.length);
        return buf;
    }

    @Override
    public void decode(byte[] buf, int off, int len){
    }
}
