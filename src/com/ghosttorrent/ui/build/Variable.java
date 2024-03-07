package com.ghosttorrent.ui.build;

public class Variable {

    private String name;
    private Object value;
    private Escape escape;

    public Variable(String name, Object value){
        this(name, value, Escape.NO_ESCAPE);
    }

    public Variable(String name, Object value, Escape escape){
        this.name = name;
        this.value = value;
        this.escape = escape;
    }

    public String getName(){
        return name;
    }

    public String getValueString(){
        if(value instanceof String){
            switch(escape){
                case ESCAPE:
                    return "new "+value+"()";

                case NO_ESCAPE:
                    return "\""+value+"\"";
            }

        }else if(value instanceof Boolean || value instanceof Number){
            return value.toString();
        }

        return "new "+value.getClass().getSimpleName()+"()";

        /*
        switch(type){
            case STRING:
                return "\""+value+"\"";

            case CLASS:
                return "new "+value+"()";

            case AS_WRITTEN:
                return value;
        }
        */
        //return null;
    }

    public Object getValue(){
        return value;
    }

    public String getClassName(){
        if(value instanceof String){
            switch(escape){
                case ESCAPE:
                    return value.toString();
            }
        }

        return value.getClass().getSimpleName();
        /*
        switch(type){
            case STRING:
                return "String";

            case CLASS:
            case AS_WRITTEN:
                return value;
        }
        return null;
        */
    }

    public enum Escape {
        ESCAPE,
        NO_ESCAPE
    }
}
