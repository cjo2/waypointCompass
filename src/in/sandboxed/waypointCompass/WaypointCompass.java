package in.sandboxed.waypointCompass;

import org.bukkit.plugin.java.JavaPlugin;

public final class WaypointCompass extends JavaPlugin {

	private WaypointManager waypointManager;
	
	@Override
	public void onEnable() {
		
		this.getLogger().info("waypointCompass version " + this.getDescription().getVersion() + " enabled.");
		this.waypointManager = new WaypointManager();
		getCommand("wc").setExecutor(new CommandManager(this));
		
	}
	
	@Override
	public void onDisable() {
		
		this.getLogger().info("waypointCompass version " + this.getDescription().getVersion()+ " disabled.");

	}
	
	public WaypointManager getWaypointManager() {
		
		return this.waypointManager;
		
	}
	
}