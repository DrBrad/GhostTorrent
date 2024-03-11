package com.ghosttorrent.torrent;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Magnet {

    /*
    PROTOCOL: magnet
    SAMPLE_HASH: xt < REMOVE urn:btih > SAMPLE_HASH
    FILE_NAME: dn
    TRACKER: tr
    */

    public Magnet(String link)throws URISyntaxException {
        //BEP 9
        URI uri = new URI(link);

        if(!uri.getScheme().equals("magnet")){
            throw new IllegalArgumentException("Link is not magnet");
        }

        Map<String, List<String>> queries = getParams(uri);

        for(String key : queries.keySet()){
            for(String value : queries.get(key)){
                System.out.println(key+" = "+value);
            }
        }
    }

    private Map<String, List<String>> getParams(URI uri){
        Map<String, List<String>> r = new HashMap<>();

        // magnet:?param1=value1...
        // uri.getSchemeSpecificPart() will start with the question mark and contain all name-value pairs
        String[] params = uri.getSchemeSpecificPart().substring(1).split("&");
        for(String param : params){
            String[] parts = param.split("=");
            String name = parts[0];
            String value = parts[1];

            if(!r.containsKey(name)){
                r.put(name, new ArrayList<>());
            }

            r.get(name).add(value);
        }

        return r;
    }
}
