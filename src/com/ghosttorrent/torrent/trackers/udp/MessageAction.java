package com.ghosttorrent.torrent.trackers.udp;

import unet.kad4.messages.inter.MessageType;

public enum MessageAction {

    CONNECT {
        public int getCode(){
            return 0;
        }
    },
    ANNOUNCE {
        public int getCode(){
            return 1;
        }
    },
    SCRAPE {
        public int getCode(){
            return 2;
        }
    },
    ERROR {
        public int getCode(){
            return 3;
        }
    }, INVALID;

    public static MessageAction fromCode(int code){
        for(MessageAction t : values()){
            if(code == t.getCode()){
                return t;
            }
        }

        return INVALID;
    }

    public int getCode(){
        return -1;
    }
}
