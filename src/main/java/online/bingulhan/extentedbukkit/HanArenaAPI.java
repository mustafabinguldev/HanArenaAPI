package online.bingulhan.extentedbukkit;

import lombok.Getter;
import online.bingulhan.extentedbukkit.bll.FileArenaManager;
import online.bingulhan.extentedbukkit.bll.IArenaManager;
import online.bingulhan.extentedbukkit.cmd.CMDArena;
import online.bingulhan.extentedbukkit.listener.ArenaListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author BingulHan
 */
public final class HanArenaAPI extends JavaPlugin {

    private static HanArenaAPI instance;

    @Getter
    private IArenaManager arenaManager;

    @Getter
    File mapsFolder;



    @Override
    public void onEnable() {
        instance=this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        mapsFolder= new File(HanArenaAPI.getInstance().getServer().getWorldContainer(), "maps");

        arenaManager = new FileArenaManager();
        getCommand("arenaapi").setExecutor(new CMDArena());

        arenaManager.removerStartup();

        if (getConfig().getBoolean("test-listeners")) {
            getServer().getPluginManager().registerEvents(new ArenaListener(), this);
        }
    }
    @Override
    public void onDisable() {
        arenaManager.clearAll();
    }

    public static HanArenaAPI getInstance() {
        return instance;
    }
}
