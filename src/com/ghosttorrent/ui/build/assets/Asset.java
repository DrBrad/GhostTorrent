package com.ghosttorrent.ui.build.assets;

import com.ghosttorrent.ui.build.Variable;

import java.util.List;

public abstract class Asset {

    public abstract List<Variable> getVariables();

    public abstract String getName();
}
