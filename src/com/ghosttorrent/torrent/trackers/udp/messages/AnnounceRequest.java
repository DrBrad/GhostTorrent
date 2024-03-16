package com.ghosttorrent.torrent.trackers.udp.messages;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.AnnounceEvent;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageAction;
import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AnnounceRequest extends MessageBase {

    /*
    Offset  Size    Name    Value
    0       64-bit integer  connection_id
    8       32-bit integer  action          1 // announce
    12      32-bit integer  transaction_id
    16      20-byte string  info_hash
    36      20-byte string  peer_id
    56      64-bit integer  downloaded
    64      64-bit integer  left
    72      64-bit integer  uploaded
    80      32-bit integer  event           0 // 0: none; 1: completed; 2: started; 3: stopped
    84      32-bit integer  IP address      0 // default
    88      32-bit integer  key
    92      32-bit integer  num_want        -1 // default
    96      16-bit integer  port
    98
    */

    //USE THE SAME KEY FOR IPv4 AND IPv6 IF WE RESOLVE FOR BOTH THEN SEND IE:
    // blanktracker.com = 1.1.1.1 & 10:ff:ff:ff:ff:etc..
    // USE SAME KEY FOR BOTH IPs

    private byte[] infoHash, peerID;
    private long connectionID, downloaded, left, uploaded;
    private AnnounceEvent event = AnnounceEvent.NONE;
    private int address = 0; //HOW SHALL WE DO THIS...?
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
        byte[] buf = new byte[98];
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
        System.arraycopy(peerID, 0, buf, 36, peerID.length);

        //DOWNLOADED
        buf[56] = ((byte) downloaded);
        buf[57] = ((byte) (downloaded >>  8));
        buf[58] = ((byte) (downloaded >> 16));
        buf[59] = ((byte) (downloaded >> 24));
        buf[60] = ((byte) (downloaded >> 32));
        buf[61] = ((byte) (downloaded >> 40));
        buf[62] = ((byte) (downloaded >> 48));
        buf[63] = ((byte) (downloaded >> 56));

        //LEFT
        buf[64] = ((byte) left);
        buf[65] = ((byte) (left >>  8));
        buf[66] = ((byte) (left >> 16));
        buf[67] = ((byte) (left >> 24));
        buf[68] = ((byte) (left >> 32));
        buf[69] = ((byte) (left >> 40));
        buf[70] = ((byte) (left >> 48));
        buf[71] = ((byte) (left >> 56));

        //UPLOADED
        buf[72] = ((byte) uploaded);
        buf[73] = ((byte) (uploaded >>  8));
        buf[74] = ((byte) (uploaded >> 16));
        buf[75] = ((byte) (uploaded >> 24));
        buf[76] = ((byte) (uploaded >> 32));
        buf[77] = ((byte) (uploaded >> 40));
        buf[78] = ((byte) (uploaded >> 48));
        buf[79] = ((byte) (uploaded >> 56));

        //EVENT
        buf[80] = ((byte) event.getCode());
        buf[81] = ((byte) (event.getCode() >> 8));
        buf[82] = ((byte) (event.getCode() >> 16));
        buf[83] = ((byte) (event.getCode() >> 24));

        //ADDRESS ( 0 for using packet origin - specify for different return )
        buf[84] = ((byte) address);
        buf[85] = ((byte) (address >> 8));
        buf[86] = ((byte) (address >> 16));
        buf[87] = ((byte) (address >> 24));

        //KEY ( RANDOMLY GENERATED - PER REQUEST )
        buf[88] = ((byte) key);
        buf[89] = ((byte) (key >> 8));
        buf[90] = ((byte) (key >> 16));
        buf[91] = ((byte) (key >> 24));

        //NUM WANT ( NUMBER OF PEERS WE WANT - DEFAULT -1)
        buf[92] = ((byte) numWant);
        buf[93] = ((byte) (numWant >> 8));
        buf[94] = ((byte) (numWant >> 16));
        buf[95] = ((byte) (numWant >> 24));

        //PORT
        buf[96] = (byte) (port & 0xff);
        buf[97] = (byte) ((port & 0xff00) >> 8);

        return buf;
    }

    @Override
    public void decode(byte[] buf){
        downloaded = (((long)(buf[0] & 0xff) << 56) |
                ((long)(buf[1] & 0xff) << 48) |
                ((long)(buf[2] & 0xff) << 40) |
                ((long)(buf[3] & 0xff) << 32) |
                ((long)(buf[4] & 0xff) << 24) |
                ((long)(buf[5] & 0xff) << 16) |
                ((long)(buf[6] & 0xff) <<  8) |
                ((long)(buf[7] & 0xff)));
    }

    public void setInfoHash(byte[] infoHash){
        this.infoHash = infoHash;
    }

    public byte[] getInfoHash(){
        return infoHash;
    }

    public void setPeerID(byte[] peerID){
        this.peerID = peerID;
    }

    public byte[] getPeerID(){
        return peerID;
    }

    public void setConnectionID(long connectionID){
        this.connectionID = connectionID;
    }

    public long getConnectionID(){
        return connectionID;
    }

    public void setDownloaded(long downloaded){
        this.downloaded = downloaded;
    }

    public long getDownloaded(){
        return downloaded;
    }

    public void setLeft(long left){
        this.left = left;
    }

    public long getLeft(){
        return left;
    }

    public void setUploaded(long uploaded){
        this.uploaded = uploaded;
    }

    public long getUploaded(){
        return uploaded;
    }

    public void setEvent(AnnounceEvent event){
        this.event = event;
    }

    public AnnounceEvent getEvent(){
        return event;
    }

    public void setAddress(Inet4Address address){
        this.address = 0;
        for(byte b : address.getAddress()){
            this.address = (this.address << 8) | (b & 0xFF);
        }
    }

    public InetAddress getAddress()throws UnknownHostException {
        return InetAddress.getByAddress(new byte[]{
            ((byte) key),
            ((byte) (key >> 8)),
            ((byte) (key >> 16)),
            ((byte) (key >> 24))
        });
    }

    public void setKey(int key){
        this.key = key;
    }

    public int getKey(){
        return key;
    }

    public void setNumWant(int numWant){
        this.numWant = numWant;
    }

    public int getNumWant(){
        return numWant;
    }

    public void setPort(int port){
        this.port = port;
    }

    public int getPort(){
        return port;
    }
}
