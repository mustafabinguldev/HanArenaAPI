package online.bingulhan.extentedbukkit.arena;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.World;

import java.io.File;
import java.util.ArrayList;

@Getter
@Setter
public class Arena {

    public static ArrayList<Arena> ARENA_LIST = new ArrayList<>();

    private String name;

    private World world;

    private String sourceWorldFolder;

    private File activeWorldFolder;


    public Arena(String name, String sourceWorldFolder) {
        this.name = name;
        this.sourceWorldFolder = sourceWorldFolder;
    }
}
