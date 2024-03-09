package com.ghosttorrent.libs.ui.res.loader.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;

public class Colors extends Assets {

    public static final String COLOR_ROOT_TAG = "colors", COLOR_TAG = "color";

    public Colors(generated.Colors colors){
        File file = new File(getClass().getResource("/style/colors.xml").getFile());

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            if(!root.getTagName().equals(COLOR_ROOT_TAG)){
                throw new IllegalArgumentException("Colors couldn't load, '"+COLOR_ROOT_TAG+"' is not root element");
            }
            NodeList nodeList = root.getElementsByTagName(COLOR_TAG);

            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);

                int id = (int) colors.getClass().getField(element.getAttribute("id")).get(colors);
                variables.put(id, Color.decode(element.getAttribute("value")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
