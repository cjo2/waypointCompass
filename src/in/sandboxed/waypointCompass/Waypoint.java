package in.sandboxed.waypointCompass;

import org.bukkit.Location;

public class Waypoint {

	private Location location;
	private String playerName;
	private boolean isPrivate;
	
	public Waypoint(String player, Location location, boolean isPrivate) {
		
		this.playerName = player;
		this.location = location;
		this.isPrivate = isPrivate;
		
	}
	
	public String getPlayer() {
		
		return this.playerName;
		
	}
	
	public Location getLocation() {
		
		return this.location;
		
	}
	
	public boolean isPrivate() {
		
		return this.isPrivate;
		
	}
	
}
