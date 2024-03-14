package com.ghosttorrent.torrent.trackers.udp;

public enum AnnounceEvent {

    NONE {
        public int getCode(){
            return 0;
        }
    },
    COMPLETED {
        public int getCode(){
            return 1;
        }
    },
    STARTED {
        public int getCode(){
            return 2;
        }
    },
    STOPPED {
        public int getCode(){
            return 3;
        }
    };

    public int getCode(){
        return 0;
    }
}
