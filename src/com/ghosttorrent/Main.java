package com.ghosttorrent;

import com.ghosttorrent.ui.GhostApplication;
import com.ghosttorrent.ui.res.build.Builder;
import com.ghosttorrent.ui.utils.inter.Application;
import generated.R;

import javax.swing.*;

public class Main {

    //HONESTLY WE COULD JUST MAKE OUR OWN COMPONENTS AND FILL WITH REFLECT - TODO

    public static void main(String[] args)throws Exception {
        //new Builder();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Application application = new GhostApplication();
        application.launch();
        //R R = new R();


        //LOAD MENU


        //R R = new R();
        //inflateMenu(R.menu.toolbar)
        //inflate will get menu from file
        //Resources


        //Res r = new Res();
        //System.out.println(r.menus.get("menu").get("file").get("open").getTitle());

        //Kademlia kad = new Kademlia();
        //kad.join(6881, InetAddress.getByName("router.bittorrent.com"), 6881);

    }
}
