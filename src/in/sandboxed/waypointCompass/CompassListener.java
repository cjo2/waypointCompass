package in.sandboxed.waypointCompass;

import org.bukkit.event.Listener;

public class CompassListener implements Listener{
	
	WaypointCompass plugin;

	public CompassListener(WaypointCompass plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
}
  