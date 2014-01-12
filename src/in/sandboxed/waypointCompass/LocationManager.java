package in.sandboxed.waypointCompass;

import java.util.HashMap;

import org.bukkit.Location;

public class LocationManager {

	private static HashMap<String,Location> locationStorage;
	
	public LocationManager() {
		
		locationStorage = new HashMap<String,Location>();
		
	}
	
	public void addLocation(String name, Location location) {
		
		locationStorage.put(name, location);
		
	}

	public void removeLocation(String name) {
		
		locationStorage.remove(name);
		
	}
	
	public boolean containsLocation(String name) {
		
		if(locationStorage.containsKey(name))
			return true;
		else
			return false;
		
	}
	
	public Location getLocation(String name) {
		
		return locationStorage.get(name);
		
	}
	
	public String getLocationList() {
		
		String toReturn = "";
		
		for(String s : locationStorage.keySet()) {
			
			Location location = locationStorage.get(s);
			toReturn += "\n  - " + s + " (" + location.getBlockX() + ", " + location.getBlockY() + ", " +location.getBlockZ() +")";
			
		}
		
		return toReturn;
		
	}
	
	public boolean deleteLocation(String name) {
		
		if(containsLocation(name)) {
			
			locationStorage.remove(name);
			return true;
			
		} else {
			
			return false;
			
		}
		
		
	}

}
