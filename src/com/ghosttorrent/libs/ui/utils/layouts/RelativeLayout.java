package com.ghosttorrent.libs.ui.utils.layouts;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RelativeLayout implements LayoutManager2 {

    private Map<Component, RelativeConstraints> constraintsMap;

    public RelativeLayout(){
        constraintsMap = new HashMap<>();
    }

    @Override
    public void addLayoutComponent(String name, Component comp){
    }

    @Override
    public void addLayoutComponent(Component component, Object constraints){
        if(constraints instanceof RelativeConstraints){
            constraintsMap.put(component, (RelativeConstraints)constraints);
        }
    }

    @Override
    public void removeLayoutComponent(Component comp){
        if(constraintsMap.containsKey(comp)){
            constraintsMap.remove(comp);
        }
    }

    @Override
    public float getLayoutAlignmentX(Container parent){
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container parent){
        return 0;
    }

    @Override
    public void invalidateLayout(Container parent){
    }

    @Override
    public Dimension preferredLayoutSize(Container parent){
        return new Dimension(0, 0);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent){
        return new Dimension(0, 0);
    }

    @Override
    public Dimension maximumLayoutSize(Container parent){
        return new Dimension(0, 0);
    }

    @Override
    public void layoutContainer(Container parent){
        Insets insets = parent.getInsets();

        for(Component component : parent.getComponents()){
            if(constraintsMap.containsKey(component)){
                RelativeConstraints constraints = constraintsMap.get(component);
                Rectangle bounds = new Rectangle(
                        insets.left+constraints.margins.left,
                        insets.top+constraints.margins.top,
                        (constraints.size.width == -1) ? component.getMinimumSize().width : constraints.size.width,
                        (constraints.size.height == -1) ? component.getMinimumSize().height : constraints.size.height);

                try{
                    if(bounds.width != -2){
                        switch(constraints.xAlignment){
                            case 0: //ALIGN PARENT LEFT
                                bounds.x = insets.left+constraints.margins.left;
                                switch(constraints.xAlignment1){
                                    case 1: // ALIGN PARENT LEFT
                                        bounds.width = parent.getWidth()-(bounds.x+constraints.margins.right+insets.right);
                                        break;
                                    case 2: // ALIGN COMPONENT RIGHT
                                        throw new Exception("Component bounds clash");
                                    case 3: // ALIGN COMPONENT LEFT
                                        bounds.width = (constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width)-(bounds.x+constraints.margins.right);
                                        break;
                                    case 4: // TO LEFT OF
                                        bounds.width = constraints.xComponent1.getBounds().x-(bounds.x+constraints.margins.right);
                                        break;
                                    case 5: // TO RIGHT OF
                                        throw new Exception("Component bounds clash");
                                }
                                break;
                            case 1: //ALIGN PARENT RIGHT
                                switch(constraints.xAlignment1){
                                    case 0: // ALIGN PARENT LEFT
                                        bounds.x = insets.left+constraints.margins.left;
                                        bounds.width = parent.getWidth()-(bounds.x+constraints.margins.right+insets.right);
                                        break;
                                    case 2: // ALIGN COMPONENT LEFT
                                        bounds.x = constraints.xComponent1.getBounds().x+constraints.margins.left;
                                        bounds.width = parent.getWidth()-(bounds.x+constraints.margins.right+insets.right);
                                        break;
                                    case 3: // ALIGN COMPONENT RIGHT
                                        throw new Exception("Component bounds clash");
                                    case 4: // TO LEFT OF
                                        throw new Exception("Component bounds clash");
                                    case 5: // TO RIGHT OF
                                        bounds.x = constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width+constraints.margins.left;
                                        bounds.width = parent.getWidth()-(bounds.x+constraints.margins.right+insets.right);
                                        break;
                                    default:
                                        bounds.x = parent.getWidth()-(bounds.width+constraints.margins.right+insets.right);
                                        break;
                                }
                                break;
                            case 2: //ALIGN COMPONENT LEFT
                                switch(constraints.xAlignment1){
                                    case 0: // ALIGN PARENT LEFT
                                        throw new Exception("Component bounds clash");
                                    case 1: // ALIGN PARENT RIGHT
                                        bounds.x = constraints.xComponent.getBounds().x;
                                        bounds.width = parent.getWidth()-(bounds.x+insets.right);
                                        break;
                                    case 3: // ALIGN COMPONENT LEFT
                                        bounds.x = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width : constraints.xComponent.getBounds().x)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent.getBounds().x : constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width)-(bounds.x+constraints.margins.right);
                                        break;
                                    case 4: // TO LEFT OF
                                        bounds.x = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent1.getBounds().x : constraints.xComponent.getBounds().x)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent.getBounds().x : constraints.xComponent1.getBounds().x)-(bounds.x+constraints.margins.right);
                                        break;
                                    case 5: // TO RIGHT OF
                                        throw new Exception("Component bounds clash");
                                    default:
                                        bounds.x = constraints.xComponent.getBounds().x+constraints.margins.left;
                                        break;
                                }
                                break;
                            case 3: //ALIGN COMPONENT RIGHT
                                switch(constraints.xAlignment1){
                                    case 0: // ALIGN PARENT LEFT
                                        bounds.x = insets.left;
                                        bounds.width = (constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width)-bounds.x;
                                        break;
                                    case 1: // ALIGN PARENT RIGHT
                                        throw new Exception("Component bounds clash");
                                    case 2: // ALIGN COMPONENT LEFT
                                        bounds.x = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent1.getBounds().x : constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width : constraints.xComponent1.getBounds().x)-(bounds.x+constraints.margins.right);
                                        break;
                                    case 4: // TO LEFT OF
                                        throw new Exception("Component bounds clash");
                                    case 5: // TO RIGHT OF
                                        bounds.x = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width : constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width : constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width)-(bounds.x+constraints.margins.right);
                                        break;
                                    default:
                                        bounds.x = (constraints.xComponent.getBounds().x+constraints.margins.right+constraints.xComponent.getBounds().width)-bounds.width;
                                        break;
                                }
                                break;
                            case 4: // TO LEFT OF
                                switch(constraints.xAlignment1){
                                    case 0: // ALIGN PARENT LEFT
                                        bounds.x = insets.left+constraints.margins.left;
                                        bounds.width = constraints.xComponent.getBounds().x-(bounds.x+constraints.margins.right);
                                        break;
                                    case 1: // ALIGN PARENT RIGHT
                                        throw new Exception("Component bounds clash");
                                    case 2: // ALIGN COMPONENT LEFT
                                        bounds.x = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent1.getBounds().x : constraints.xComponent.getBounds().x)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent.getBounds().x : constraints.xComponent1.getBounds().x)-(bounds.x+constraints.margins.right);
                                        break;
                                    case 3: // ALIGN COMPONENT RIGHT
                                        throw new Exception("Component bounds clash");
                                    case 5: // TO RIGHT OF
                                        bounds.x = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width : constraints.xComponent.getBounds().x)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent.getBounds().x : constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width)-(bounds.x+constraints.margins.right);
                                        break;
                                    default:
                                        bounds.x = constraints.xComponent.getBounds().x-(bounds.width+constraints.margins.right);
                                        break;
                                }
                                break;
                            case 5: // TO RIGHT OF
                                switch(constraints.xAlignment1){
                                    case 0: // ALIGN PARENT LEFT
                                        throw new Exception("Component bounds clash");
                                    case 1: // ALIGN PARENT RIGHT
                                        bounds.x = constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width+constraints.margins.left;
                                        bounds.width = parent.getWidth()-(bounds.x+constraints.margins.right+insets.right);
                                        break;
                                    case 2: // ALIGN COMPONENT LEFT
                                        throw new Exception("Component bounds clash");
                                    case 3: // ALIGN COMPONENT RIGHT
                                        bounds.x = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width : constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width) ?
                                                constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width : constraints.xComponent1.getBounds().x+constraints.xComponent1.getBounds().width)-(bounds.x+constraints.margins.right);
                                        break;
                                    case 4: // TO LEFT OF
                                        bounds.x = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent1.getBounds().x : constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width)+constraints.margins.left;
                                        bounds.width = ((constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width > constraints.xComponent1.getBounds().x) ?
                                                constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width : constraints.xComponent1.getBounds().x)-(bounds.x+constraints.margins.right);
                                        break;
                                    default:
                                        bounds.x = constraints.xComponent.getBounds().x+constraints.xComponent.getBounds().width+constraints.margins.left;
                                        break;
                                }
                                break;
                            case 6: // CENTER
                                bounds.x = (parent.getWidth()-(insets.left+insets.right+bounds.width))/2;
                                break;
                        }
                    }else{
                        bounds.width = parent.getWidth()-(bounds.x+insets.right);
                    }

                    //MARGINS WORKING
                    if(bounds.height != -2){
                        switch(constraints.yAlignment){
                            case 0: //ALIGN PARENT TOP
                                bounds.y = insets.top+constraints.margins.top;
                                switch(constraints.yAlignment1){
                                    case 1: // ALIGN PARENT BOTTOM
                                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.bottom+insets.bottom);
                                        break;
                                    case 2: // ALIGN COMPONENT TOP
                                        throw new Exception("Component bounds clash");
                                    case 3: // ALIGN COMPONENT BOTTOM
                                        bounds.height = (constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 4: // ABOVE
                                        bounds.height = constraints.yComponent1.getBounds().y-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 5: // BELOW
                                        throw new Exception("Component bounds clash");
                                }
                                break;
                            case 1: //ALIGN PARENT BOTTOM
                                switch(constraints.yAlignment1){
                                    case 0: // ALIGN PARENT TOP
                                        bounds.y = insets.top+constraints.margins.top;
                                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.bottom+insets.bottom);
                                        break;
                                    case 2: // ALIGN COMPONENT TOP
                                        bounds.y = constraints.yComponent1.getBounds().y+constraints.margins.top;
                                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.bottom+insets.bottom);
                                        break;
                                    case 3: // ALIGN COMPONENT BOTTOM
                                        throw new Exception("Component bounds clash");
                                    case 4: // ABOVE
                                        throw new Exception("Component bounds clash");
                                    case 5: // BELOW
                                        bounds.y = constraints.yComponent1.getBounds().y+constraints.margins.top+constraints.yComponent1.getBounds().height;
                                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.bottom+insets.bottom);
                                        break;
                                    default:
                                        bounds.y = parent.getHeight()-(bounds.height+constraints.margins.bottom+insets.bottom);
                                        break;
                                }
                                break;
                            case 2: //ALIGN COMPONENT TOP
                                switch(constraints.yAlignment1){
                                    case 0: // ALIGN PARENT TOP
                                        throw new Exception("Component bounds clash");
                                    case 1: // ALIGN PARENT BOTTOM
                                        bounds.y = constraints.yComponent.getBounds().y+constraints.margins.top;
                                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.bottom+insets.bottom);
                                        break;
                                    case 3: // ALIGN COMPONENT BOTTOM
                                        bounds.y = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height : constraints.yComponent.getBounds().y)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent.getBounds().y : constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 4: // ABOVE
                                        bounds.y = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent1.getBounds().y : constraints.yComponent.getBounds().y)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent.getBounds().y : constraints.yComponent1.getBounds().y)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 5: // BELOW
                                        throw new Exception("Component bounds clash");
                                    default:
                                        bounds.y = constraints.yComponent.getBounds().y+constraints.margins.top;
                                        break;
                                }
                                break;
                            case 3: //ALIGN COMPONENT BOTTOM
                                switch(constraints.yAlignment1){
                                    case 0: // ALIGN PARENT TOP
                                        bounds.y = insets.top+constraints.margins.top;
                                        bounds.height = (constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 1: // ALIGN PARENT BOTTOM
                                        throw new Exception("Component bounds clash");
                                    case 2: // ALIGN COMPONENT TOP
                                        bounds.y = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent1.getBounds().y : constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height : constraints.yComponent1.getBounds().y)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 4: // ABOVE
                                        throw new Exception("Component bounds clash");
                                    case 5: // BELOW
                                        bounds.y = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height : constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height : constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    default:
                                        bounds.y = (constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height)-(bounds.height+constraints.margins.bottom);
                                        break;
                                }
                                break;
                            case 4: // ABOVE
                                switch(constraints.yAlignment1){
                                    case 0: // ALIGN PARENT TOP
                                        bounds.y = insets.top+constraints.margins.top;
                                        bounds.height = constraints.yComponent.getBounds().y-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 1: // ALIGN PARENT BOTTOM
                                        throw new Exception("Component bounds clash");
                                    case 2: // ALIGN COMPONENT TOP
                                        bounds.y = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent1.getBounds().y : constraints.yComponent.getBounds().y)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent.getBounds().y : constraints.yComponent1.getBounds().y)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 3: // ALIGN COMPONENT BOTTOM
                                        throw new Exception("Component bounds clash");
                                    case 5: // BELOW
                                        bounds.y = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height : constraints.yComponent.getBounds().y)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent.getBounds().y : constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    default:
                                        bounds.y = constraints.yComponent.getBounds().y-(bounds.height+constraints.margins.bottom);
                                        break;
                                }
                                break;
                            case 5: // BELOW
                                switch(constraints.yAlignment1){
                                    case 0: // ALIGN PARENT TOP
                                        throw new Exception("Component bounds clash");
                                    case 1: // ALIGN PARENT BOTTOM
                                        bounds.y = constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height+constraints.margins.top;
                                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.bottom+insets.bottom);
                                        break;
                                    case 2: // ALIGN COMPONENT TOP
                                        throw new Exception("Component bounds clash");
                                    case 3: // ALIGN COMPONENT BOTTOM
                                        bounds.y = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height : constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height) ?
                                                constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height : constraints.yComponent1.getBounds().y+constraints.yComponent1.getBounds().height)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    case 4: // ABOVE
                                        bounds.y = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent1.getBounds().y : constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height)+constraints.margins.top;
                                        bounds.height = ((constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height > constraints.yComponent1.getBounds().y) ?
                                                constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height : constraints.yComponent1.getBounds().y)-(bounds.y+constraints.margins.bottom);
                                        break;
                                    default:
                                        bounds.y = constraints.yComponent.getBounds().y+constraints.yComponent.getBounds().height+constraints.margins.top;
                                        break;
                                }
                                break;
                            case 6: // CENTER
                                bounds.y = (parent.getHeight()-(insets.top+insets.bottom+bounds.height))/2;
                                break;
                        }
                    }else{
                        bounds.height = parent.getHeight()-(bounds.y+constraints.margins.top+constraints.margins.bottom+insets.bottom);
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }

                component.setBounds(bounds);
            }else{
                component.setBounds(insets.top, insets.left, component.getMinimumSize().width, component.getMinimumSize().height);
            }
        }
    }

    public void setConstraints(Component component, RelativeConstraints constraints){
        constraintsMap.put(component, constraints);
    }

    public RelativeConstraints getConstraints(Component component){
        return constraintsMap.get(component);
    }
}