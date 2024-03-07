package com.ghosttorrent.ui.utils.res.menus;

import org.w3c.dom.Element;

public class MenuOption {

    public static final String MENU_OPTION_TAG = "option";

    private String title;

    public MenuOption(Element root){
        if(!root.getTagName().equals(MENU_OPTION_TAG)){
            throw new IllegalArgumentException("Menu couldn't load, '"+MENU_OPTION_TAG+"' is not set as tag");
        }

        title = root.getAttribute("title");
    }

    public String getTitle(){
        return title;
    }
}
