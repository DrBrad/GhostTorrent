package com.ghosttorrent.ui.res.loader;

import com.ghosttorrent.R;
import com.ghosttorrent.ui.res.loader.assets.*;
import com.ghosttorrent.ui.res.views.View;
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
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Resources {

    public Map<String, Assets> assets;
    private R R;

    public Resources(R R){
        this.R = R;
        assets = new HashMap<>();
        assets.put("color", new Colors(R.color));
        assets.put("string", new Strings(R.string));
        assets.put("image", new Images(R.image));
        assets.put("menu", new Menus(R.menu));
        assets.put("layout", new Layouts(R.layout));
    }

    public Object findById(String asset, int id){
        return assets.get(asset).get(id);
    }

    public View inflate(String asset, int id){
        String name = (String) assets.get(asset).get(id);

        File file = new File(getClass().getResource("/"+asset+"/"+name+".xml").getFile());

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            return (View) inflateLayout(null, doc.getDocumentElement());

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private JComponent inflateLayout(View parent, Element root)throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> c = Class.forName(root.getTagName());
        JComponent component = (JComponent) c.getDeclaredConstructor().newInstance();


        if(parent != null){
            try{
                int id = (int) R.id.getClass().getField(root.getAttribute("id")).get(R.id);
                parent.addView(id, component);
                //((Ids) assets.get("id")).add(id, component);
            }catch(NoSuchFieldException e){
            }
        }

        for(int i = 0; i < root.getAttributes().getLength(); i++){
            String name = root.getAttributes().item(i).getNodeName();
            if(name.equals("id")){// || name.equals("layout")){
                continue;
            }

            Object value;
            Class<?> param;

            switch(name){
                case "icon":
                    param = Icon.class;
                    value = new ImageIcon(resolveImage(root.getAttributes().item(i).getNodeValue()));
                    break;

                case "layout":
                    param = LayoutManager.class;
                    value = Class.forName(root.getAttributes().item(i).getNodeValue()).getDeclaredConstructor().newInstance();
                    break;

                case "foreground":
                case "background":
                    param = Color.class;
                    value = resolveColor(root.getAttributes().item(i).getNodeValue());
                    break;

                default:
                    param = String.class;
                    value = resolveString(root.getAttributes().item(i).getNodeValue());
                    break;
            }

            try{
                name = "set"+(name.charAt(0)+"").toUpperCase()+name.substring(1);
                Method method = component.getClass().getMethod(name, param);
                method.invoke(component, value);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(root.hasChildNodes()){
            NodeList nodeList = root.getChildNodes();

            for(int i = 0; i < root.getChildNodes().getLength(); i++){
                if(nodeList.item(i).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }
                inflateLayout((View) component, (Element) nodeList.item(i));
            }
        }

        return component;
    }

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
