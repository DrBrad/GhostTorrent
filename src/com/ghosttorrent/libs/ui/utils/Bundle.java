package com.ghosttorrent.libs.ui.utils;

import java.util.HashMap;

public class Bundle {

    private HashMap<String, Object> variables;

    public Bundle(){
        variables = new HashMap<>();
    }

    public boolean containsKey(String key){
        return variables.containsKey(key);
    }

    public Object getString(String key){
        return variables.get(key);
    }

    public void put(String key, Object value){
        variables.put(key, value);
    }
}
