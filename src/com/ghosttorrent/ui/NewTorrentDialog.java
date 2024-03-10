package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.Bundle;
import com.ghosttorrent.libs.ui.utils.inter.Dialog;

public class NewTorrentDialog extends Dialog {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        frame.setSize(480, 160);
        setContentView(R.layout.new_torrent_dialog);

        frame.setTitle("New Torrent");
        //frame.setMinimumSize(new Dimension(480, 160));
        //frame.setMaximumSize(new Dimension(480, 160));
    }
}
