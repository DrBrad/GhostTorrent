package com.ghosttorrent.ui.res.build.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Views extends Asset {

    private String name;

    public Views(File file){
        this.name = file.getName().split("\\.")[0];

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            recursive(doc.getDocumentElement());

            //if(!root.getTagName().equals(COLOR_ROOT_TAG)){
            //    throw new IllegalArgumentException("Colors couldn't load, '"+COLOR_ROOT_TAG+"' is not root element");
            //}
            /*
            NodeList nodeList = root.getElementsByTagName("menu");

            for(int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                variables.put(element.getAttribute("id"), i);//new Variable(, element.getAttribute("value")));
            }*/

        }catch(Exception e){
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
            variables.put(element.getAttribute("id"), i);
            recursive(element);
        }
    }

    @Override
    public String getPackage(){
        return super.getPackage()+".menu";
    }

    @Override
    public String getName(){
        return name;
    }
}
