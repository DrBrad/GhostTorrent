package com.ghosttorrent.libs.ui.res.build.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Strings extends Assets {

    public static final String SRING_ROOT_TAG = "strings", SRING_TAG = "string";

    public Strings(){
        File file = new File(getClass().getResource("/style/strings.xml").getFile());

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            if(!root.getTagName().equals(SRING_ROOT_TAG)){
                throw new IllegalArgumentException("Strings couldn't load, '"+SRING_ROOT_TAG+"' is not root element");
            }
            NodeList nodeList = root.getElementsByTagName(SRING_TAG);

            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                String name = element.getAttribute("id");
                variables.add(new Variable(name, name.hashCode(), "int"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getName(){
        return "Strings";
    }
}
