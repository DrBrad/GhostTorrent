package com.ghosttorrent.ui.res.build.assets;

import java.util.ArrayList;
import java.util.List;

public abstract class Assets {

    private String packageName, packagePath;
    protected List<Variable> variables;
    protected List<String> imports;

    public Assets(){
        packageName = "generated";
        packagePath = System.getProperty("user.dir")+"/src/";
        variables = new ArrayList<>();
        imports = new ArrayList<>();
    }

    public abstract String getName();

    public boolean hasImports(){
        return !imports.isEmpty();
    }

    public List<String> getImports(){
        return imports;
    }

    public String getExtend(){
        return null;
    }

    public String getImplements(){
        return null;
    }

    public String getPackageName(){
        return packageName;
    }

    public String getPackagePath(){
        return packagePath+getPackageName().replaceAll("\\.", "/")+"/"+getName()+".java";
    }

    public List<Variable> getVariables(){
        return variables;
    }

    public class Variable {

        private String key, type;
        private Object value;

        public Variable(String key, Object value, String type){
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public String getKey(){
            return key;
        }

        public Object getValue(){
            return value;
        }

        public String getType(){
            return type;
        }
    }
}
