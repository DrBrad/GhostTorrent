package com.ghosttorrent.torrent;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Magnet {

    public Magnet()throws URISyntaxException {
        //BEP 9
        URI uri = new URI("magnet:?xt=urn:btih:4f836d43e43e8682ff6d2e197e32d7e4f4fb6af4&dn=Ubuntu+Linux+20.04+LTS+Desktop+64-bit&tr=http%3A%2F%2Ftorrent.ubuntu.com%3A6969%2Fannounce");
        //URL url = new URL("magnet:?xt=urn:btih:4f836d43e43e8682ff6d2e197e32d7e4f4fb6af4&dn=Ubuntu+Linux+20.04+LTS+Desktop+64-bit&tr=http%3A%2F%2Ftorrent.ubuntu.com%3A6969%2Fannounce");

        if(!uri.getScheme().equals("magnet")){
            System.err.println("NOT MAGNET");
        }

        System.out.println(uri.getSchemeSpecificPart());

        if(uri.getQuery().length() > 0){
            Map<String, String> queries = splitQuery(uri.getQuery());

            for(String key : queries.keySet()){
                System.out.println(key+" = "+queries.get(key));
            }
        }
    }

    public static Map<String, String> splitQuery(String query)throws UnsupportedEncodingException {
        Map<String, String> r = new HashMap<>();
        for(String pair : query.split("&")){
            int idx = pair.indexOf("=");
            r.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return r;
    }
}
