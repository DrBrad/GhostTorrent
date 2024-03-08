package com.ghosttorrent.ui.res.loader;

import com.ghosttorrent.ui.res.loader.assets.Asset;
import com.ghosttorrent.ui.res.loader.assets.Colors;
import com.ghosttorrent.ui.res.loader.assets.Images;
import com.ghosttorrent.ui.res.loader.assets.Menus;
import generated.R;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    public Map<String, Asset> assets;

    public Resources(R R){
        assets = new HashMap<>();
        assets.put("color", new Colors(R.color));
        assets.put("image", new Images(R.image));
        assets.put("menu", new Menus(R.menu));
        //assets.put("id", );
    }

    public Object findById(String asset, int id){
        return assets.get(asset).get(id);
    }

    public JComponent inflate(String asset, int id){
        String name = (String) assets.get(asset).get(id);
        System.out.println(name);

        File file = new File(getClass().getResource("/menu/"+name+".xml").getFile());

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            return inflate(doc.getDocumentElement());

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private JComponent inflate(Element root)throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> c = Class.forName(root.getTagName());
        JComponent component = (JComponent) c.getDeclaredConstructor().newInstance();

        if(component instanceof AbstractButton){
            if(root.hasAttribute("title")){
                System.out.println(root.getAttribute("title"));
                ((AbstractButton) component).setText(root.getAttribute("title"));
            }
        }

        if(root.hasAttribute("background")){
            String color = root.getAttribute("background");
            if(color.startsWith("@color/")){
                //color.replaceFirst("@color/", "")
                //component.setBackground((Color) assets.get("color").get());
            }else{
                component.setBackground(Color.decode(color));
            }
        }

        if(root.hasChildNodes()){
            NodeList nodeList = root.getChildNodes();

            for(int i = 0; i < root.getChildNodes().getLength(); i++){
                if(nodeList.item(i).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }
                component.add(inflate((Element) nodeList.item(i)));
            }
        }

        return component;
    }
}
