package com.ghosttorrent.torrent.trackers.udp;

import java.net.InetAddress;

public class AnnounceRequest extends MessageBase {

    private byte[] infoHash, peerID;
    private long connectionID, downloaded, left, uploaded;
    private AnnounceEvent event;
    private InetAddress address;
    private int key, numWant = -1, port;

    public AnnounceRequest(){
        action = MessageAction.ANNOUNCE;
    }

    public AnnounceRequest(byte[] tid){
        super(tid);
        action = MessageAction.ANNOUNCE;
    }

    @Override
    public byte[] encode(){
        byte[] buf = new byte[16];
        //System.arraycopy(connectionID, 0, buf, 0, connectionID.length);

        buf[0] = ((byte) connectionID);
        buf[1] = ((byte) (connectionID >>  8));
        buf[2] = ((byte) (connectionID >> 16));
        buf[3] = ((byte) (connectionID >> 24));
        buf[4] = ((byte) (connectionID >> 32));
        buf[5] = ((byte) (connectionID >> 40));
        buf[6] = ((byte) (connectionID >> 48));
        buf[7] = ((byte) (connectionID >> 56));

        buf[8] = ((byte) action.getCode());
        buf[9] = ((byte) (action.getCode() >> 8));
        buf[10] = ((byte) (action.getCode() >> 16));
        buf[11] = ((byte) (action.getCode() >> 24));

        System.arraycopy(tid, 0, buf, 12, tid.length);

        System.arraycopy(infoHash, 0, buf, 16, infoHash.length);
        System.arraycopy(peerID, 0, buf, 26, peerID.length);

        //DOWNLOADED
        buf[27] = ((byte) downloaded);
        buf[28] = ((byte) (downloaded >>  8));
        buf[29] = ((byte) (downloaded >> 16));
        buf[30] = ((byte) (downloaded >> 24));
        buf[31] = ((byte) (downloaded >> 32));
        buf[32] = ((byte) (downloaded >> 40));
        buf[33] = ((byte) (downloaded >> 48));
        buf[34] = ((byte) (downloaded >> 56));

        //LEFT
        buf[35] = ((byte) left);
        buf[36] = ((byte) (left >>  8));
        buf[37] = ((byte) (left >> 16));
        buf[38] = ((byte) (left >> 24));
        buf[39] = ((byte) (left >> 32));
        buf[40] = ((byte) (left >> 40));
        buf[41] = ((byte) (left >> 48));
        buf[42] = ((byte) (left >> 56));

        //UPLOADED
        buf[43] = ((byte) uploaded);
        buf[44] = ((byte) (uploaded >>  8));
        buf[45] = ((byte) (uploaded >> 16));
        buf[46] = ((byte) (uploaded >> 24));
        buf[47] = ((byte) (uploaded >> 32));
        buf[48] = ((byte) (uploaded >> 40));
        buf[49] = ((byte) (uploaded >> 48));
        buf[50] = ((byte) (uploaded >> 56));

        //EVENT
        buf[51] = ((byte) event.getCode());
        buf[52] = ((byte) (event.getCode() >> 8));
        buf[53] = ((byte) (event.getCode() >> 16));
        buf[54] = ((byte) (event.getCode() >> 24));

        //ADDRESS

        //KEY

        //NUM WANT

        //PORT

        return buf;
    }

    @Override
    public void decode(byte[] buf){
        downloaded = (((long)(buf [0] & 0xff) << 56) |
                ((long)(buf [1] & 0xff) << 48) |
                ((long)(buf [2] & 0xff) << 40) |
                ((long)(buf [3] & 0xff) << 32) |
                ((long)(buf [4] & 0xff) << 24) |
                ((long)(buf [5] & 0xff) << 16) |
                ((long)(buf [6] & 0xff) <<  8) |
                ((long)(buf [7] & 0xff)));
    }
}
