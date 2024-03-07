package com.ghosttorrent.ui.build.assets;

import java.util.Map;

public abstract class Asset {

    protected Map<String, Object> variables;

    public Map<String, Object> getVariables(){
        return variables;
    }

    public abstract String getName();
}
