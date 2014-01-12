package in.sandboxed.waypointCompass;

import java.util.HashMap;

import org.bukkit.Location;

public class WaypointManager {

	private HashMap<String,Waypoint> waypointStorage;
	
	public WaypointManager() {
		
		this.waypointStorage = new HashMap<String,Waypoint>();
		
	}
	
	public void addWaypoint(String name, String player, Location location, boolean isPrivate) {
		
		this.waypointStorage.put(name, new Waypoint(player, location, isPrivate));
		
	}
	
	public boolean containsWaypoint(String name) {
		
		if(this.waypointStorage.containsKey(name))
			return true;
		else
			return false;
		
	}
	
	public boolean removeWaypoint(String name, String player) {
		
		if(!this.containsWaypoint(name) || !this.waypointStorage.get(name).getPlayer().equals(player)) {
			
			return false;
			
		} else {
			
			this.waypointStorage.remove(name);
			return true;
			
		}
		
	}
	
	public Waypoint getWaypoint(String name) {
		
		return this.waypointStorage.get(name);
		
	}
	
	public HashMap<String,Waypoint> getWaypointsForPlayer(String player) {
		
		HashMap<String,Waypoint> toReturn = new HashMap<String,Waypoint>();
		
		for(String key : this.waypointStorage.keySet()) {
			
			Waypoint current = this.waypointStorage.get(key);
			
			if(!current.isPrivate()) {
				
				toReturn.put(key, current);
				
			} else if (current.getPlayer().equals(player)) {
				
				toReturn.put(key, current);
				
			}

		}
		
		return toReturn;
		
	}
	
}
