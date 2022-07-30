package online.bingulhan.extentedbukkit.events;

import lombok.Getter;
import online.bingulhan.extentedbukkit.arena.Arena;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class ArenaRemoveEvent extends Event{
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private String arena;


    public ArenaRemoveEvent(String arena) {
        this.arena=arena;

    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
