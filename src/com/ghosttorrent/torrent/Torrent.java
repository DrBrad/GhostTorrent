package com.ghosttorrent.torrent;

import unet.kad4.libs.bencode.variables.BencodeObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Torrent {

    private String announce, comment, createdBy;
    private List<String> announceList;
    private long creationDate;

    public Torrent(File file){
        try{
            InputStream in = new FileInputStream(file);
            byte[] buf = new byte[(int) file.length()];
            in.read(buf);

            BencodeObject ben = new BencodeObject(buf);

            if(ben.containsKey("announce")){
                announce = ben.getString("announce");
            }

            if(ben.containsKey("announce-list")){
                announceList = new ArrayList<>();
                for(int i = 0; i < ben.getBencodeArray("announce-list").size(); i++){
                    //announceList.add(ben.getBencodeArray("announce-list").getString(i));
                }
            }

            if(ben.containsKey("comment")){
                comment = ben.getString("comment");
            }

            if(ben.containsKey("created by")){
                createdBy = ben.getString("created by");
            }

            if(ben.containsKey("creation date")){
                creationDate = ben.getLong("creation date");
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public String getAnnounce(){
        return announce;
    }

    public List<String> getAnnounceList(){
        return announceList;
    }

    public String getComment(){
        return comment;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public long getCreationDate(){
        return creationDate;
    }
}
