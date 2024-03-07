package com.ghosttorrent.ui.res.build.assets;

import java.io.File;

public class Menus extends Asset {

    //public static final String COLOR_ROOT_TAG = "colors", COLOR_TAG = "color";

    public Menus(){
        File dir = new File(getClass().getResource("/menu").getFile());

        //GENERATE MENUS DIR
        //GENERATE ANOTHER FILE...

        int i = 0;
        for(File file : dir.listFiles()){
            variables.add(new Variable(file.getName().split("\\.")[0], i, "int"));

            //DECODE FILE
            new Ids(file, "menu").generate();



            i++;
        }


        /*
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
                variables.put(element.getAttribute("id"), i);//new Variable(, element.getAttribute("value")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        */
    }

    @Override
    public String getName(){
        return "Menus";
    }
}
