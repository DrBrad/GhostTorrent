package com.ghosttorrent.ui.res.loader.assets;

import java.util.HashMap;
import java.util.Map;

public class Asset {

    protected Map<Integer, Object> variables;

    public Asset(){
        variables = new HashMap<>();
    }

    public Object get(int i){
        return variables.get(i);
    }
}
