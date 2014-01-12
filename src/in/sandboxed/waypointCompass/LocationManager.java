package in.sandboxed.waypointCompass;

import java.util.HashMap;

import org.bukkit.Location;

public class LocationManager {

	private HashMap<String,Location> locationStorage;
	
	public LocationManager() {
		
		this.locationStorage = new HashMap<String,Location>();
		
	}
	
	public void addLocation(String name, Location location) {
		
		this.locationStorage.put(name, location);
		
	}

	public void removeLocation(String name) {
		
		this.locationStorage.remove(name);
		
	}
	
	public boolean containsLocation(String name) {
		
		if(this.locationStorage.containsKey(name))
			return true;
		else
			return false;
		
	}
	
	public Location getLocation(String name) {
		
		return this.locationStorage.get(name);
		
	}
	
	public String getLocationList() {
		
		String toReturn = "";
		
		for(String s : this.locationStorage.keySet()) {
			
			Location location = this.locationStorage.get(s);
			toReturn += "\n  - " + s + " (" + location.getBlockX() + ", " + location.getBlockY() + ", " +location.getBlockZ() +")";
			
		}
		
		return toReturn;
		
	}
	
	public boolean deleteLocation(String name) {
		
		if(containsLocation(name)) {
			
			this.locationStorage.remove(name);
			return true;
			
		} else {
			
			return false;
			
		}
		
		
	}

}
