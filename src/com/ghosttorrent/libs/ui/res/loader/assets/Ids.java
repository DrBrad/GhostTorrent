package com.ghosttorrent.libs.ui.res.loader.assets;

import javax.swing.*;

public class Ids extends Assets {

    public void add(int key, JComponent component){
        variables.put(key, component);
    }
}
