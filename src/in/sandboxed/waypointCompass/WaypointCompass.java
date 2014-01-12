package in.sandboxed.waypointCompass;

import org.bukkit.plugin.java.JavaPlugin;

public final class WaypointCompass extends JavaPlugin {

	private LocationManager locationManager;
	
	@Override
	public void onEnable() {
		
		this.getLogger().info("waypointCompass version " + this.getDescription().getVersion() + " enabled.");
		this.locationManager = new LocationManager();
		getCommand("wc").setExecutor(new CommandManager(this));
		
	}
	
	@Override
	public void onDisable() {
		
		this.getLogger().info("waypointCompass version " + this.getDescription().getVersion()+ " disabled.");

	}

	public LocationManager getLocationManager() {
		
		return this.locationManager;
		
	}
	
}