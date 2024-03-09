package com.ghosttorrent.libs.ui.res.build.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Ids extends Assets {

    public void parse(File file){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            recursive(doc.getDocumentElement());

        }catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }

    private void recursive(Element root){
        if(!root.hasChildNodes()){
            return;
        }

        NodeList nodeList = root.getChildNodes();

        for(int i = 0; i < root.getChildNodes().getLength(); i++){
            if(nodeList.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) nodeList.item(i);
            recursive(element);

            if(!element.hasAttribute("id")){
                continue;
            }

            String name = element.getAttribute("id");
            variables.add(new Variable(name, name.hashCode(), "int"));
        }
    }

    @Override
    public String getName(){
        return "Ids";
    }
}
