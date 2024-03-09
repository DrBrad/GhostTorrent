package com.ghosttorrent.libs.ui.res.loader.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Strings extends Assets {

    public static final String STRING_ROOT_TAG = "strings", STRING_TAG = "string";

    public Strings(generated.Strings strings){
        File file = new File(getClass().getResource("/style/strings.xml").getFile());

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            if(!root.getTagName().equals(STRING_ROOT_TAG)){
                throw new IllegalArgumentException("Colors couldn't load, '"+STRING_ROOT_TAG+"' is not root element");
            }
            NodeList nodeList = root.getElementsByTagName(STRING_TAG);

            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);

                int id = (int) strings.getClass().getField(element.getAttribute("id")).get(strings);
                variables.put(id, element.getAttribute("value"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
