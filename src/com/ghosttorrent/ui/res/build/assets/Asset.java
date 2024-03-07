package com.ghosttorrent.ui.res.build.assets;

import java.util.HashMap;
import java.util.Map;

public abstract class Asset {

    protected Map<String, Object> variables;

    public Asset(){
        variables = new HashMap<>();
    }

    public Map<String, Object> getVariables(){
        return variables;
    }

    public abstract String getName();
}
