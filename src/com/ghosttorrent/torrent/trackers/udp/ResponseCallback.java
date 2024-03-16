package com.ghosttorrent.torrent.trackers.udp;

import com.ghosttorrent.torrent.trackers.udp.messages.inter.MessageBase;

public interface ResponseCallback {

    void onResponse(MessageBase message);
}
