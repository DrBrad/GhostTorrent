package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.Bundle;
import com.ghosttorrent.libs.ui.utils.inter.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PreferencesDialog extends Dialog {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        frame.setSize(480, 160);
        setContentView(R.layout.preferences_dialog);

        frame.setTitle(findStringById(R.string.app_name)+" Preferences");
        //frame.setMinimumSize(new Dimension(480, 160));
        //frame.setMaximumSize(new Dimension(480, 160));


        /*
        JTabbedPane tabbedPane = new JTabbedPane();
        //ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panel1 = new JPanel();
        tabbedPane.addTab("Tab 1", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = new JPanel();
        tabbedPane.addTab("Tab 2", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = new JPanel();
        tabbedPane.addTab("Tab 3", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = new JPanel();
        //panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        getRoot().add(tabbedPane);
        */
    }
}
