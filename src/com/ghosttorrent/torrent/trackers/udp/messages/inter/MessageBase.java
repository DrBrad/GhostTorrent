package com.ghosttorrent.torrent.trackers.udp.messages.inter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public abstract class MessageBase {

    protected MessageAction action;
    protected byte[] tid;
    protected InetSocketAddress origin, destination;

    public MessageBase(){
    }

    public MessageBase(byte[] tid){
        this.tid = tid;
    }

    public MessageAction getAction(){
        return action;
    }

    public byte[] getTransactionID(){
        return tid;
    }

    public void setTransactionID(byte[] tid){
        this.tid = tid;
    }

    public abstract byte[] encode();

    public abstract void decode(byte[] buf, int off, int len);

    public InetSocketAddress getDestination(){
        return destination;
    }

    public InetAddress getDestinationAddress(){
        return destination.getAddress();
    }

    public int getDestinationPort(){
        return destination.getPort();
    }

    public void setDestination(InetAddress address, int port){
        destination = new InetSocketAddress(address, port);
    }

    public void setDestination(InetSocketAddress destination){
        this.destination = destination;
    }

    public InetSocketAddress getOrigin(){
        return origin;
    }

    public InetAddress getOriginAddress(){
        return origin.getAddress();
    }

    public int getOriginPort(){
        return origin.getPort();
    }

    public void setOrigin(InetAddress address, int port){
        origin = new InetSocketAddress(address, port);
    }

    public void setOrigin(InetSocketAddress origin){
        this.origin = origin;
    }
}
