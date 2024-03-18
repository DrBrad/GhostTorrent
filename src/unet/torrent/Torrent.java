package unet.torrent;

import unet.bencode.io.BencodeReader;
import unet.bencode.variables.BencodeObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Torrent {

    private String announce, comment, createdBy;
    private List<String> announceList;
    private long creationDate;
    private TorrentInfo info;

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
                info = new TorrentInfo(ben.getBencodeObject("info"));
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

    public TorrentInfo getInfo(){
        return info;
    }
}
