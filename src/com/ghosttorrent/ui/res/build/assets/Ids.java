package com.ghosttorrent.ui.res.build.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Ids extends Assets {

    public void parse(File file){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            recursive(doc.getDocumentElement(), 0);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private int recursive(Element root, int x){
        if(!root.hasChildNodes()){
            return x;
        }

        NodeList nodeList = root.getChildNodes();

        for(int i = 0; i < root.getChildNodes().getLength(); i++){
            if(nodeList.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) nodeList.item(i);
            String name = element.getAttribute("id");
            variables.add(new Variable(name, Math.abs(name.hashCode()), "int"));
            x++;
            recursive(element, x);
        }
        return x;
    }

    @Override
    public String getName(){
        return "Ids";
    }
}
