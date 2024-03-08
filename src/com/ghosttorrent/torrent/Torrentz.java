package com.ghosttorrent.torrent;

public class Torrentz {

    private String title;
    private int peers;
    private long obtained, total;

    public Torrentz(){

    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setPeers(int peers){
        this.peers = peers;
    }

    public int getPeers(){
        return peers;
    }

    public void setObtained(long obtained){
        this.obtained = obtained;
    }

    public long getObtained(){
        return obtained;
    }

    public void setTotal(long total){
        this.total = total;
    }

    public long getTotal(){
        return total;
    }
}
