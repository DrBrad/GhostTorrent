package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.inter.Dialog;
import com.ghosttorrent.libs.ui.utils.Bundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenURLDialog extends Dialog {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.open_url_dialog);

        frame.setTitle("Open URL");
        frame.setSize(500, 300);
        frame.setMinimumSize(new Dimension(500, 300));


        getRoot().setLayout(new BoxLayout(getRoot(), BoxLayout.Y_AXIS));
        //getRoot().setBackground(Color.RED);

        /*
        ((JButton) findViewById(R.id.dialog_open)).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLICKED");
            }
        });*/

        //System.out.println(findViewById(R.id.));

    }
}
