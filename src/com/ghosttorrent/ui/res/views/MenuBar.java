package com.ghosttorrent.ui.res.views;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MenuBar extends JMenuBar implements View {

    private Map<Integer, JComponent> components;

    public MenuBar(){
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
