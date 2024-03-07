package com.ghosttorrent.ui.res.build;

import com.ghosttorrent.ui.res.build.assets.*;

public class Builder {

    public Builder(){
        Asset[] assets = new Asset[]{
                new Res(),
                new Colors(),
                new Images(),
                new Menus()
        };

        for(Asset asset : assets){
            asset.generate();
        }
    }
}
