package unet.torrent.trackers.udp;

import unet.torrent.trackers.udp.messages.inter.MessageBase;

public interface ResponseCallback {

    void onResponse(MessageBase message);
}
