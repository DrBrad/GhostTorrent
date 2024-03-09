package com.ghosttorrent.libs.ui.res.views;

import javax.swing.*;

public interface View {

    JComponent findViewById(int id);

    void addView(int id, JComponent component);
}
