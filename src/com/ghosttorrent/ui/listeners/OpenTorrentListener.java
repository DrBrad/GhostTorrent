package com.ghosttorrent.ui.listeners;

import com.ghosttorrent.torrent.Torrent;
import com.ghosttorrent.torrent.trackers.DHTTracker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.NoSuchAlgorithmException;

public class OpenTorrentListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e){
        FileDialog dialog = new FileDialog((Frame) null);
        //dialog.setFilenameFilter(new FilenameFilter().accept(null, ""));
        dialog.setMultipleMode(true);
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

        StringBuilder builder = new StringBuilder();
        for(byte b : torrent.getInfoHash()){
            builder.append(String.format("%02x", b));
        }

        System.out.println(builder);

        new DHTTracker(torrent.getInfoHash());
    }
}
