package com.ghosttorrent.ui.build.assets;

import com.ghosttorrent.ui.build.Variable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colors extends Asset {

    public static final String COLOR_ROOT_TAG = "colors", COLOR_TAG = "color";

    public Colors(){
        variables = new HashMap<>();

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
                variables.put(element.getAttribute("id"), 1);//new Variable(, element.getAttribute("value")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getName(){
        return "Colors";
    }
}
