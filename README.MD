A perfect arena resetter and builder for your Minigames

Developers:

Owner:
   https://github.com/BingulHan/ 

Support https://discord.gg/bingulhan

Maven:
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
   
   <dependency>
	    <groupId>com.github.BingulHan</groupId>
	    <artifactId>HanArenaAPI</artifactId>
	    <version>0.1.1</version>
	</dependency>
```


How to use Custom Event
```
public class TestListener implements Listener {
    //Custom events.
    @EventHandler
    public void event(ArenaCreateEvent e) {
        //TODO
    }
}
```

How to use API 
```

//Get API plugin
 private HanArenaAPI hanArenaAPI = (HanArenaAPI) Bukkit.getServer().getPluginManager().getPlugin("HanArenaAPI");
 
 public void testAdd(String arenaName, String sourceName) { 
        Arena arena = new Arena(arenaName, sourceName);
        hanArenaAPI.getArenaManager().loadArena(arena);  
 }
 
  public void testRemove(String arenaName) { 
        Arena arena = hanArenaAPI.getArenaManager().getArena(arenaName).get();
        hanArenaAPI.getArenaManager().clear(arenaName);
 }
 
  public void testReset(String arenaName) { 
        Arena arena = hanArenaAPI.getArenaManager().getArena(arenaName).get();
        hanArenaAPI.getArenaManager().resetArena(arenaName);
 }
```
