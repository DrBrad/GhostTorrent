package com.ghosttorrent.ui.res.loader;

import com.ghosttorrent.ui.res.loader.assets.Asset;
import com.ghosttorrent.ui.res.loader.assets.Colors;
import com.ghosttorrent.ui.res.loader.assets.Images;
import generated.R;

import java.util.HashMap;
import java.util.Map;

public class Resources {

    public Map<String, Asset> assets;

    public Resources(R R){
        assets = new HashMap<>();
        assets.put("color", new Colors(R.color));
        assets.put("image", new Images(R.image));
    }

    public Object findById(String asset, int id){
        return assets.get(asset).get(id);
    }
}
