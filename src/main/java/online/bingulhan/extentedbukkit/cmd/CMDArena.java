package online.bingulhan.extentedbukkit.cmd;

import online.bingulhan.extentedbukkit.HanArenaAPI;
import online.bingulhan.extentedbukkit.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CMDArena implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length>0) {
            if (args[0].equals("create")) {
                if (args.length>1) {
                    String arena = args[1];
                    if (args.length>2) {
                        String source = args[2];

                        if (arena != null && source != null) {

                            if (!HanArenaAPI.getInstance().getArenaManager().hasArenaFolder(source))  {
                                sender.sendMessage(ChatColor.RED+"Source file not found, put your world in maps folder to add source file. (Caution: World folders should not have uuid.dat )");
                                return true;
                            }
                            Arena a = new Arena(arena, source);
                            Arena.ARENA_LIST.add(a);

                            HanArenaAPI.getInstance().getArenaManager().loadArena(a);

                            sender.sendMessage(ChatColor.GREEN+"Arena created");
                        }else {
                            sender.sendMessage(ChatColor.RED+"Arena name and resource file cannot be null.");
                        }
                    }
                }
            }

            if (args[0].equals("reset")) {
                if (args.length>1) {
                    String arena = args[1];
                    if (HanArenaAPI.getInstance().getArenaManager().isArenaCreated(arena)) {

                        Optional<Arena> ar = HanArenaAPI.getInstance().getArenaManager().getArena(arena);
                        if (!ar.isPresent()) return false;

                        HanArenaAPI.getInstance().getArenaManager().resetArena(ar.get());

                        sender.sendMessage(ChatColor.GREEN+"Arena reset");
                        sender.sendMessage(ChatColor.GREEN+"Arena reloaded");

                    }else{
                        sender.sendMessage("Not found : "+arena);
                    }


                }
            }

            if (args[0].equals("remove")) {
                if (args.length>1) {
                    String arena = args[1];
                    if (HanArenaAPI.getInstance().getArenaManager().isArenaCreated(arena)) {

                        Optional<Arena> ar = HanArenaAPI.getInstance().getArenaManager().getArena(arena);
                        if (!ar.isPresent()) return false;

                        HanArenaAPI.getInstance().getArenaManager().clear(ar.get());

                        sender.sendMessage(ChatColor.GREEN+"Arena deleted!");

                    }else{
                        sender.sendMessage("Not found : "+arena);
                    }


                }
            }

            if (args[0].equals("tp")) {
                if (args.length>1) {
                    String arena = args[1];
                    if (HanArenaAPI.getInstance().getArenaManager().isArenaCreated(arena)) {
                        Arena ar = HanArenaAPI.getInstance().getArenaManager().getArena(arena).get();
                        Player p = (Player)sender;
                        p.teleport(new Location(ar.getWorld(), 0, 100, 0));

                        sender.sendMessage(ChatColor.GREEN+"Teleported to the arena: "+ar.getName());


                    }else{
                        sender.sendMessage("Not Found : "+arena);
                    }
                }
            }

            if (args[0].equals("list")) {
                sender.sendMessage(ChatColor.DARK_GREEN+"Arenas: ");
                Arena.ARENA_LIST.stream().forEach(arena -> sender.sendMessage(ChatColor.WHITE+"- "+arena.getName()));
            }
        }else {
            sender.sendMessage(ChatColor.GREEN+"/arenaapi create <arena name> <source folder>"+ChatColor.RED+"(Caution: World folders should not have uuid.dat )");
            sender.sendMessage(ChatColor.GREEN+"/arenaapi reset <arena name>");
            sender.sendMessage(ChatColor.GREEN+"/arenaapi tp <arena name>");
            sender.sendMessage(ChatColor.GREEN+"/arenaapi remove <arena name>");
            sender.sendMessage(ChatColor.GREEN+"/arenaapi list");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (args.length>0 && args.length<2) {
            list.add("remove");
            list.add("create");
            list.add("tp");
            list.add("reset");

        }

        if (args.length>1) {

            if (args.length<3) {
                if (args[0].equals("create") || args[0].equals("reset") ||args[0].equals("remove") || args[0].equals("tp")) {
                    Arena.ARENA_LIST.stream().forEach(arena -> list.add(arena.getName()));
                }

            }else {
                if (args.length>2) {

                    if (args.length<4) {
                        if (args[0].equals("create")) {
                            Arrays.stream(HanArenaAPI.getInstance().getMapsFolder().listFiles()).forEach(f -> list.add(f.getName()));
                        }
                    }else {
                        list.add("Hello I Am BingulHan");
                    }
                }
            }
        }
        return list;
    }
}
