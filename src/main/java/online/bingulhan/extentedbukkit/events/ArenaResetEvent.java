package online.bingulhan.extentedbukkit.events;

import lombok.Getter;
import online.bingulhan.extentedbukkit.arena.Arena;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class ArenaResetEvent extends Event{
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private Arena arena;


    public ArenaResetEvent(Arena arena) {
        this.arena=arena;

    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
