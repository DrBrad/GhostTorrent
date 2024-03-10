package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.Bundle;
import com.ghosttorrent.libs.ui.utils.inter.Dialog;

public class PreferencesDialog extends Dialog {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        frame.setSize(480, 160);
        setContentView(R.layout.preferences_dialog);

        frame.setTitle(findStringById(R.string.app_name)+" Preferences");
        //frame.setMinimumSize(new Dimension(480, 160));
        //frame.setMaximumSize(new Dimension(480, 160));
    }
}
