package com.ghosttorrent.ui.res.views;

import com.ghosttorrent.ui.utils.layouts.RelativeConstraints;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Panel extends JPanel implements View {

    private Map<Integer, JComponent> components;

    public Panel(){
        super();
        components = new HashMap<>();
    }

    public JComponent findViewById(int id){
        if(!components.containsKey(id)){
            for(JComponent component : components.values()){
                if(component instanceof View){
                    JComponent c = ((View) component).findViewById(id);
                    if(c != null){
                        return c;
                    }
                }
            }

            return null;
        }

        return components.get(id);
    }

    public void addView(int id, JComponent component){
        add(component);
        components.put(id, component);
    }
}
