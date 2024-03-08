package com.ghosttorrent.ui.res.loader;

import com.ghosttorrent.ui.res.loader.assets.*;
import generated.R;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    public Map<String, Assets> assets;
    private R R;

    public Resources(R R){
        this.R = R;
        assets = new HashMap<>();
        assets.put("color", new Colors(R.color));
        assets.put("image", new Images(R.image));
        assets.put("menu", new Menus(R.menu));
        assets.put("layout", new Layouts(R.layout));
        assets.put("id", new Ids());
        //assets.put("id", );
    }

    public Object findById(String asset, int id){
        return assets.get(asset).get(id);
    }

    public JComponent inflate(String asset, int id){
        String name = (String) assets.get(asset).get(id);

        File file = new File(getClass().getResource("/"+asset+"/"+name+".xml").getFile());

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

        try{
            int id = (int) R.id.getClass().getField(root.getAttribute("id")).get(R.id);
            ((Ids) assets.get("id")).add(id, component);
        }catch(NoSuchFieldException e){
        }

        /*
        if(!root.hasAttribute("border")){
            switch(root.getTagName()){
                case "javax.swing.JMenuBar":
                    ((JMenuBar) component).setBorderPainted(false);
                    break;

                case "javax.swing.JButton":
                    ((JButton) component).setBorderPainted(false);
                    break;

                case "javax.swing.JProgressBar":
                    ((JProgressBar) component).setBorderPainted(false);
                    break;
            }
            component.setBorder(null);
        }
        */

        for(int i = 0; i < root.getAttributes().getLength(); i++){
            switch(root.getAttributes().item(i).getNodeName()){
                case "title":
                    if(component instanceof AbstractButton){
                        ((AbstractButton) component).setText(root.getAttributes().item(i).getNodeValue());
                    }
                    break;

                case "text-color":
                    component.setForeground(resolveColor(root.getAttributes().item(i).getNodeValue()));
                    break;

                case "background":
                    component.setBackground(resolveColor(root.getAttributes().item(i).getNodeValue()));
                    break;

                case "src":
                    if(component instanceof JLabel){
                        ((JLabel) component).setIcon(new ImageIcon(resolveImage(root.getAttributes().item(i).getNodeValue())));
                    }

                    if(component instanceof JButton){
                        //((JButton) component).setIcon(new ImageIcon(resolveImage(root.getAttributes().item(i).getNodeValue())));
                    }
                    break;
            /*
                case "border":
                    String[] dimensions = root.getAttributes().item(i).getNodeValue().split(" ");
                    component.setBorder(new MatteBorder(
                            Integer.parseInt(dimensions[0]),
                            Integer.parseInt(dimensions[1]),
                            Integer.parseInt(dimensions[2]),
                            Integer.parseInt(dimensions[3]),
                            root.hasAttribute("border-color") ? resolveColor(root.getAttribute("border-color")) : Color.WHITE));
                    break;
            */
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

    /*
    public String resolveString(String string){
        if(string.startsWith("@string/")){
            try{
                int id = (int) R.string.getClass().getField(string.replaceFirst("@string/", "")).get(R.string);
                return (String) assets.get("string").get(id);

            }catch(NoSuchFieldException | IllegalAccessException e){
                e.printStackTrace();
            }

            return null;
        }

        return string;
    }
    */

    private Color resolveColor(String color){
        if(color.startsWith("@color/")){
            try{
                int id = (int) R.color.getClass().getField(color.replaceFirst("@color/", "")).get(R.color);
                return (Color) assets.get("color").get(id);

            }catch(NoSuchFieldException | IllegalAccessException e){
                e.printStackTrace();
            }

            return null;
        }

        return Color.decode(color);
    }

    private BufferedImage resolveImage(String image){
        if(image.startsWith("@image/")){
            try{
                int id = (int) R.image.getClass().getField(image.replaceFirst("@image/", "")).get(R.image);
                return (BufferedImage) assets.get("image").get(id);

            }catch(NoSuchFieldException | IllegalAccessException e){
                e.printStackTrace();
            }

            return null;
        }

        try{
            return ImageIO.read(new File(image));
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
