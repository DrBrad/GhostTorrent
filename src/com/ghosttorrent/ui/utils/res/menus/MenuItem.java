package com.ghosttorrent.ui.utils.res.menus;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ghosttorrent.ui.utils.res.menus.MenuOption.MENU_OPTION_TAG;

public class MenuItem {

    public static final String MENU_ITEM_TAG = "item";
    private String title;
    private Map<String, MenuOption> options;

    public MenuItem(Element root){
        options = new HashMap<>();

        if(!root.getTagName().equals(MENU_ITEM_TAG)){
            throw new IllegalArgumentException("Menu couldn't load, '"+MENU_ITEM_TAG+"' is not set as tag");
        }
        title = root.getAttribute("title");

        NodeList nodeList = root.getElementsByTagName(MENU_OPTION_TAG);

        for(int i = 0; i < nodeList.getLength(); i++){
            Element element = (Element) nodeList.item(i);
            options.put(element.getAttribute("id"), new MenuOption(element));
        }
    }

    public String getTitle(){
        return title;
    }

    public MenuOption get(String id){
        return options.get(id);
    }

    public MenuOption[] getAllOptions(){
        MenuOption[] r = new MenuOption[options.size()];
        options.values().toArray(r);
        return r;
    }
}
