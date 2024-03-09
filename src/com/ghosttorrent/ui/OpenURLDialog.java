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
        frame.setSize(480, 160);
        frame.setMinimumSize(new Dimension(480, 160));
        frame.setMaximumSize(new Dimension(480, 160));


        getRoot().setLayout(new BoxLayout(getRoot(), BoxLayout.Y_AXIS));

        //((JLabel) findViewById(R.id.dialog_title)).setHorizontalAlignment(SwingConstants.LEFT);
        //.setFont(UIManager.getFont(""));

        //findViewById(R.id.dialog_url_parent).setMaximumSize(new Dimension(480, 60));
        GridBagLayout urlLayout = (GridBagLayout) findViewById(R.id.dialog_url_parent).getLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;

        urlLayout.setConstraints(findViewById(R.id.dialog_url_text), constraints);

        //findViewById(R.id.dialog_url).setMinimumSize(new Dimension(300, 80));
        constraints.insets = new Insets(0, 0, 0, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 1;
        constraints.gridheight = 2;
        urlLayout.setConstraints(findViewById(R.id.dialog_url), constraints);

        ((FlowLayout) findViewById(R.id.dialog_buttons).getLayout()).setAlignment(FlowLayout.RIGHT);
        //getRoot().setBackground(Color.RED);

        ((JButton) findViewById(R.id.dialog_cancel)).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                onDestroy();
            }
        });

        //System.out.println(findViewById(R.id.));

    }
}
