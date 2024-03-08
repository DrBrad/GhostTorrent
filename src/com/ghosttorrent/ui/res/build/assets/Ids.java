package com.ghosttorrent.ui.res.build.assets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Ids extends Asset {

    private String name, type;

    public Ids(File file, String type){
        name = file.getName().split("\\.")[0];
        this.type = type;

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            recursive(doc.getDocumentElement());

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
            variables.add(new Variable(element.getAttribute("id"), i, "int"));
            recursive(element);
        }
    }

    @Override
    public String getImplements(){
        return "com.ghosttorrent.ui.res.Ids";
    }

    @Override
    public String getPackage(){
        return super.getPackage()+"."+type;
    }

    @Override
    public String getName(){
        return name;
    }
}
