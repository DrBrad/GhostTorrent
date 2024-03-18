package unet.torrent;

import unet.bencode.variables.BencodeObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class TorrentInfo {

    public static final int PIECE_LENGTH = 20;

    private String name;
    private long pieceLength;
    private byte[] infoHash;
    private List<byte[]> pieces;
    private List<TorrentFile> files;

    public TorrentInfo(BencodeObject ben)throws NoSuchAlgorithmException  {
        if(ben.containsKey("name")){
            name = ben.getString("name");
        }

        if(ben.containsKey("piece length")){
            pieceLength = ben.getLong("piece length");
        }

        if(ben.containsKey("pieces")){
            pieces = new ArrayList<>();

            for(int i = 0; i < ben.getBytes("pieces").length/PIECE_LENGTH; i++){
                byte[] buf = new byte[20];
                System.arraycopy(ben.getBytes("pieces"), i*20, buf, 0, PIECE_LENGTH);
                pieces.add(buf);
            }
        }

        if(ben.containsKey("files")){
            files = new ArrayList<>();

            for(int i = 0; i < ben.getBencodeArray("files").size(); i++){
                files.add(new TorrentFile(ben.getBencodeArray("files").getBencodeObject(i)));
            }
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        infoHash = digest.digest(ben.encode());
    }

    public String getName(){
        return name;
    }

    public long getPieceLength(){
        return pieceLength;
    }

    public List<byte[]> getPieces(){
        return pieces;
    }

    public List<TorrentFile> getFiles(){
        return files;
    }

    public byte[] getHash(){
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
