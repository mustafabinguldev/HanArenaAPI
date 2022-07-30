package online.bingulhan.extentedbukkit.bll;

import online.bingulhan.extentedbukkit.HanArenaAPI;
import online.bingulhan.extentedbukkit.arena.Arena;
import online.bingulhan.extentedbukkit.events.ArenaCreateEvent;
import online.bingulhan.extentedbukkit.events.ArenaRemoveEvent;
import online.bingulhan.extentedbukkit.events.ArenaResetEvent;
import online.bingulhan.extentedbukkit.util.FileUtil;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;;

public class FileArenaManager implements IArenaManager{

    public static ArrayList<String> ARENA_LIST = new ArrayList<>();

    protected File mapsFolder;

    public FileArenaManager() {
        mapsFolder = HanArenaAPI.getInstance().getMapsFolder();
        if (!mapsFolder.exists()) {
            mapsFolder.mkdir();
        }
    }

    @Override
    public boolean hasArenaFolder(Arena arena) {
        return Arrays.stream(mapsFolder.listFiles()).anyMatch(folder -> folder.getName().equals(arena.getSourceWorldFolder()));
    }

    @Override
    public boolean hasArenaFolder(String folder) {
        return Arrays.stream(mapsFolder.listFiles()).anyMatch(f -> f.getName().equals(folder));
    }

    @Override
    public void resetArena(Arena arena) {

        ARENA_LIST.remove(arena.getWorld().getName());

        if (arena.getWorld() != null) HanArenaAPI.getInstance().getServer().unloadWorld(arena.getWorld(), false);
        if (arena.getActiveWorldFolder() != null) FileUtil.delete(arena.getActiveWorldFolder());

        arena.setWorld(null);
        arena.setActiveWorldFolder(null);

        HanArenaAPI.getInstance().getLogger().info("Arena sifirlandi! ");

        HanArenaAPI.getInstance().getServer().getPluginManager().callEvent(new ArenaResetEvent(arena));

        loadArena(arena, true);

    }

    @Override
    public void loadArena(Arena arena) {
        if (arena.getWorld() != null) return;

        String key = "HAPI"+arena.getName()+"_"+System.currentTimeMillis();
        File file  =new File(HanArenaAPI.getInstance().getServer().getWorldContainer(), key);
        file.mkdir();

        if (getSourceFolder(arena.getSourceWorldFolder()) != null) {
            File source = getSourceFolder(arena.getSourceWorldFolder());
                    try {
                        FileUtil.copy(source, file);
                    }catch (Exception e) {
                        HanArenaAPI.getInstance().getLogger().info("There was a problem copying the source world.");
                        e.printStackTrace();
                    }

                HanArenaAPI.getInstance().getServer().getScheduler().runTaskLater(HanArenaAPI.getInstance(), () -> {
                    World w = HanArenaAPI.getInstance().getServer().createWorld(new WorldCreator(key));

                    arena.setWorld(w);
                    arena.setActiveWorldFolder(file);

                    ARENA_LIST.add(key);

                    HanArenaAPI.getInstance().getLogger().info("Arena olusturuldu! ");

                    HanArenaAPI.getInstance().getServer().getPluginManager().callEvent(new ArenaCreateEvent(arena));
                }, 40);





        }else {
            HanArenaAPI.getInstance().getLogger().info("Kaynak dunyası bulunamadı.");
        }
    }


    @Override
    public void loadArena(Arena arena, boolean reset) {
        if (arena.getWorld() != null) return;

        String key = "HAPI"+arena.getName()+"_"+System.currentTimeMillis();
        File file  =new File(HanArenaAPI.getInstance().getServer().getWorldContainer(), key);
        file.mkdir();

        if (getSourceFolder(arena.getSourceWorldFolder()) != null) {
            File source = getSourceFolder(arena.getSourceWorldFolder());
            try {
                FileUtil.copy(source, file);
            }catch (Exception e) {
                HanArenaAPI.getInstance().getLogger().info("There was a problem copying the source world.");
                e.printStackTrace();
            }

            HanArenaAPI.getInstance().getServer().getScheduler().runTaskLater(HanArenaAPI.getInstance(), () -> {
                World w = HanArenaAPI.getInstance().getServer().createWorld(new WorldCreator(key));

                arena.setWorld(w);
                arena.setActiveWorldFolder(file);

                ARENA_LIST.add(key);

                HanArenaAPI.getInstance().getLogger().info("Arena olusturuldu! ");
            }, 40);





        }else {
            HanArenaAPI.getInstance().getLogger().info("Kaynak dunyası bulunamadı.");
        }
    }


    @Override
    public boolean isArenaCreated(String arena) {
        return Arena.ARENA_LIST.stream().anyMatch(a -> a.getName().equals(arena));
    }

    @Override
    public Optional<Arena> getArena(String arena) {
        for (Arena  a : Arena.ARENA_LIST) {
            if (a.getName().equals(arena)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    @Override
    public void clearAll() {
        Arena.ARENA_LIST.stream().forEach(a -> {clear(a);});
    }

    @Override
    public void clear(Arena arena) {

            HanArenaAPI.getInstance().getServer().getPluginManager().callEvent(new ArenaRemoveEvent(arena.getName()));
           resetArena(arena);
           Arena.ARENA_LIST.remove(arena);

    }

    @Override
    public void removerStartup() {
        Arrays.stream(HanArenaAPI.getInstance().getServer().getWorldContainer().listFiles()).forEach(file -> {
            if (file.isDirectory() && file.canWrite() && file.getName().startsWith("HAPI")) {
                FileUtil.delete(file);
            }
        });
    }

    private File getSourceFolder(String folder) throws NullPointerException{
        for (File f : mapsFolder.listFiles()) {
            if (f.getName().equals(folder)) {
                return f;
            }
        }

        return null;
    }


}
