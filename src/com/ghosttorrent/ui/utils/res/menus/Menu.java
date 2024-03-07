package com.ghosttorrent.ui.utils.res.menus;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ghosttorrent.ui.utils.res.menus.MenuItem.MENU_ITEM_TAG;

public class Menu {

    public static final String MENU_TAG = "menu";
    private Map<String, MenuItem> items;

    public Menu(Document doc){
        Element root = doc.getDocumentElement();
        items = new HashMap();

        if(!root.getTagName().equals(MENU_TAG)){
            throw new IllegalArgumentException("Menu couldn't load, '"+MENU_TAG+"' is not root element");
        }

        NodeList nodeList = root.getElementsByTagName(MENU_ITEM_TAG);

        for(int i = 0; i < nodeList.getLength(); i++){
            Element element = (Element) nodeList.item(i);
            //if (node.getNodeType() == Node.ELEMENT_NODE) {
            //String id = element.getAttribute("title");
            //System.out.println("Value: " + id);
            //}
            items.put(element.getAttribute("id"), new MenuItem(element));
        }
    }

    public MenuItem get(String id){
        return items.get(id);
    }

    public MenuItem[] getAllItems(){
        MenuItem[] r = new MenuItem[items.size()];
        items.values().toArray(r);
        return r;
    }
}
