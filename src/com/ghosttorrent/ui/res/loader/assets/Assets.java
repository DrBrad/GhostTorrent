package com.ghosttorrent.ui.res.loader.assets;

import java.util.HashMap;
import java.util.Map;

public class Assets {

    protected Map<Integer, Object> variables;

    public Assets(){
        variables = new HashMap<>();
    }

    public Object get(int i){
        return variables.get(i);
    }
}
