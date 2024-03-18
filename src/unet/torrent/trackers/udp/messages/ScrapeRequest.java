package unet.torrent.trackers.udp.messages;

import unet.torrent.trackers.udp.messages.inter.MessageAction;
import unet.torrent.trackers.udp.messages.inter.MessageBase;

public class ScrapeRequest extends MessageBase {

    public ScrapeRequest(){
        action = MessageAction.SCRAPE;
    }

    public ScrapeRequest(byte[] tid){
        super(tid);
        action = MessageAction.SCRAPE;
    }

    @Override
    public byte[] encode(){
        return new byte[0];
    }

    @Override
    public void decode(byte[] buf, int off, int len){

    }
}
