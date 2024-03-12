package com.ghosttorrent.torrent.messages;

import unet.bencode.variables.BencodeObject;
import unet.kad4.messages.inter.Message;
import unet.kad4.messages.inter.MessageType;
import unet.kad4.messages.inter.MethodMessageBase;

@Message(method = "announce_peer", type = MessageType.RSP_MSG)
public class AnnouncePeerResponse extends MethodMessageBase {

    /*
    arguments:  {"id" : "<querying nodes id>",
      "implied_port": <0 or 1>,
      "info_hash" : "<20-byte infohash of target torrent>",
      "port" : <port number>,
      "token" : "<opaque token>"}

    response: {"id" : "<queried nodes id>"}

    Example Packets:
    announce_peers Query = {"t":"aa", "y":"q", "q":"announce_peer", "a": {"id":"abcdefghij0123456789", "implied_port": 1, "info_hash":"mnopqrstuvwxyz123456", "port": 6881, "token": "aoeusnth"}}
    bencoded = d1:ad2:id20:abcdefghij012345678912:implied_porti1e9:info_hash20:mnopqrstuvwxyz1234564:porti6881e5:token8:aoeusnthe1:q13:announce_peer1:t2:aa1:y1:qe

    Response = {"t":"aa", "y":"r", "r": {"id":"mnopqrstuvwxyz123456"}}
    bencoded = d1:rd2:id20:mnopqrstuvwxyz123456e1:t2:aa1:y1:re
    */

    public AnnouncePeerResponse(byte[] tid){
        super(tid);
    }

    @Override
    public BencodeObject encode(){
        return super.encode();
    }

    @Override
    public void decode(BencodeObject ben){
        super.decode(ben);
    }
}
