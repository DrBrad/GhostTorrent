package com.ghosttorrent.torrent;

import unet.bencode.io.BencodeReader;
import unet.bencode.variables.BencodeObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Torrent {

    private String announce, comment, createdBy, name;
    private List<String> announceList;
    private List<TorrentFile> files;
    private long creationDate, pieceLength;

    private byte[] infoHash;

    public Torrent(File file){
        try{
            BencodeReader reader = new BencodeReader(new FileInputStream(file));
            BencodeObject ben = (BencodeObject) reader.read();
            reader.close();

            if(ben.containsKey("announce")){
                announce = ben.getString("announce");
            }

            if(ben.containsKey("announce-list")){
                announceList = new ArrayList<>();
                for(int i = 0; i < ben.getBencodeArray("announce-list").size(); i++){
                    announceList.add(ben.getBencodeArray("announce-list").getBencodeArray(i).getString(0));
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

            System.out.println(ben);

            if(ben.containsKey("info")){
                if(ben.getBencodeObject("info").containsKey("name")){
                    name = ben.getBencodeObject("info").getString("name");
                }

                if(ben.getBencodeObject("info").containsKey("piece length")){
                    pieceLength = ben.getBencodeObject("info").getLong("piece length");
                }

                if(ben.getBencodeObject("info").containsKey("files")){
                    files = new ArrayList<>();

                    for(int i = 0; i < ben.getBencodeObject("info").getBencodeArray("files").size(); i++){
                        files.add(new TorrentFile(ben.getBencodeObject("info").getBencodeArray("files").getBencodeObject(i)));
                    }
                }

                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                infoHash = digest.digest(ben.getBencodeObject("info").encode());
            }

        }catch(IOException | NoSuchAlgorithmException ex){
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

    public String getName(){
        return name;
    }

    public long getPieceLength(){
        return pieceLength;
    }

    public List<TorrentFile> getFiles(){
        return files;
    }

    public byte[] getInfoHash(){
        return infoHash;
    }

    public class TorrentFile {

        private List<String> path;
        private long length;

        public TorrentFile(BencodeObject ben){
            if(ben.containsKey("length")){
                length = ben.getLong("length");
            }

            if(ben.containsKey("path")){
                path = new ArrayList<>();

                for(int i = 0; i < ben.getBencodeArray("path").size(); i++){
                    path.add(ben.getBencodeArray("path").getString(i));
                }
            }
        }

        public long getLength(){
            return length;
        }

        public List<String> getPath(){
            return path;
        }
    }
}
