package com.ghosttorrent.torrent.trackers;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class Tracker {

    private TrackerType type;
    private InetAddress address;
    private int port;

    public Tracker(URI uri)throws UnknownHostException {
        type = TrackerType.getFromScheme(uri.getScheme());
        address = InetAddress.getByName(uri.getHost());
        port = uri.getPort();
    }

    public TrackerType getType(){
        return type;
    }

    public InetAddress getAddress(){
        return address;
    }

    public int getPort(){
        return port;
    }

    public enum TrackerType {
        UDP {
            public String getScheme(){
                return "udp";
            }
        },
        HTTP {
            public String getScheme(){
                return "http";
            }
        }, INVALID;

        public static TrackerType getFromScheme(String scheme){
            for(TrackerType type : TrackerType.values()){
                if(scheme.equals(type.getScheme())){
                    return type;
                }
            }

            return INVALID;
        }

        public String getScheme(){
            return null;
        }
    }
}
