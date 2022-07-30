package online.bingulhan.extentedbukkit.listener;

import online.bingulhan.extentedbukkit.HanArenaAPI;
import online.bingulhan.extentedbukkit.events.ArenaCreateEvent;
import online.bingulhan.extentedbukkit.events.ArenaRemoveEvent;
import online.bingulhan.extentedbukkit.events.ArenaResetEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaListener implements Listener {


    //Custom events.
    @EventHandler
    public void event(ArenaCreateEvent e) {
       HanArenaAPI.getInstance().getServer().broadcastMessage(ChatColor.GREEN+"Arena successfully created "+e.getArena().getName());
    }

    @EventHandler
    public void event(ArenaRemoveEvent e) {
        HanArenaAPI.getInstance().getServer().broadcastMessage(ChatColor.GREEN+"Arena deleted successfully "+e.getArena());
    }

    @EventHandler
    public void event(ArenaResetEvent e) {
        HanArenaAPI.getInstance().getServer().broadcastMessage(ChatColor.GREEN+"Arena successfully reset "+e.getArena().getName());
    }



}
