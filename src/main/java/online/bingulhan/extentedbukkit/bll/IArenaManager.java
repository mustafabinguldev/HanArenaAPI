package online.bingulhan.extentedbukkit.bll;

import online.bingulhan.extentedbukkit.arena.Arena;

/**
 * version: v0.1
 * @author BingulHan
 */
public interface IArenaManager {

    /**
     * Checks if the arena file exists
     * @param arena
     * @return
     */
    boolean hasArenaFolder(Arena arena);

    boolean hasArenaFolder(String folder);

    void resetArena(Arena arena);

    void loadArena(Arena arena);

    void loadArena(Arena arena, boolean reset);

    /**
     * Checks if the arena has formed
     * @param arena
     * @return
     */
    boolean isArenaCreated(String arena);


    Arena getArena(String arena);

    void clearAll();
    void clear(Arena arena);

    /**
     * It deletes files that are not deleted due to error or crash.
     */
    void removerStartup();






}
