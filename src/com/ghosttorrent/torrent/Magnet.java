package com.ghosttorrent.torrent;

import java.net.URI;
import java.net.URISyntaxException;
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

    - GET ANNOUNCE LIST
    - TAKE CONNECTION-ID FROM ANNOUNCE AND SAVE FOR 30 MINUTE ANNOUNCE
    - DO A GET_PEERS????
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

        //BEP 3 - NO REQUIREMENTS TO TRACKERS
        //BEP 48 - MUST CONTAIN /announce IN URI
    }

    private Map<String, List<String>> getParams(URI uri){
        Map<String, List<String>> r = new HashMap<>();

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
