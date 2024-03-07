package com.ghosttorrent.ui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    private Map<Integer, Object> assets;

    public Resources(){
        assets = new HashMap<>();
    }

    public Color findColorById(int id){
        return Color.decode((String) assets.get(id));
    }

    public BufferedImage getImageById(int id){
        return (BufferedImage) assets.get(id);
    }
}
