package com.ghosttorrent.ui.listeners;

import com.ghosttorrent.torrent.Torrent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenTorrentListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e){
        FileDialog dialog = new FileDialog((Frame) null);
        dialog.setVisible(true);

        if(dialog.getDirectory() == null ||
                dialog.getFile() == null){
            return;
        }

        File file = new File(dialog.getDirectory()+"/"+dialog.getFile());
        Torrent torrent = new Torrent(file);
        System.out.println(torrent.getAnnounce());
        System.out.println(torrent.getComment());
        System.out.println(torrent.getCreatedBy());
        System.out.println(torrent.getFiles().get(0).getPath().get(0));
    }
}
