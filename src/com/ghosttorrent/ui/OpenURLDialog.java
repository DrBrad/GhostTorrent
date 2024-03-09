package com.ghosttorrent.ui;

import com.ghosttorrent.libs.ui.utils.inter.Dialog;
import com.ghosttorrent.libs.ui.utils.Bundle;

import java.awt.*;

public class OpenURLDialog extends Dialog {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        //setContentView(R.layout.open_url_dialog);

        frame.setSize(500, 800);
        frame.setMinimumSize(new Dimension(500, 800));
    }
}
