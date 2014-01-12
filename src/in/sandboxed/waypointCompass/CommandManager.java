package in.sandboxed.waypointCompass;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{

	private WaypointCompass plugin;
	
	public CommandManager(WaypointCompass plugin) {
		
		this.plugin = plugin;
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		if(args.length<1) {
			sender.sendMessage(ChatColor.RED + "Not enough arguments!");
			return true;
		}
		
		switch(args[0]) {
		case "mark": {
			if(args.length<2) {
				sender.sendMessage(ChatColor.RED + "You did not name the location (/wc mark locationName)");
				break;
			}
			this.markLocation((Player) sender, args[1], ((Player) sender).getLocation());
			break;
		}
		case "goto":
			this.pointToLocation((Player) sender, args[1]);
			break;
		case "list":
			this.getLocations((Player) sender);
			break;
		case "delete":
			this.deleteLocation((Player) sender, args[1]);
			break;
		}
		
		
		return true;
	}
	
	private void markLocation(Player player, String name, Location location) {
		
		this.plugin.getLocationManager().addLocation(name, location);
		player.sendMessage(ChatColor.YELLOW + "Waypoint '" + name + "' saved!");
	}
	
	private void pointToLocation(Player player, String name) {
		
		if(!this.plugin.getLocationManager().containsLocation(name)) {
			player.sendMessage(ChatColor.RED + "Waypoint '" + name + "' does not exist.");
			return;
		}
		
		player.setCompassTarget(this.plugin.getLocationManager().getLocation(name));
		player.sendMessage(ChatColor.YELLOW + "Waypoint '" + name + "' set! Use a compass to guide you to the location.");
		
	}
	
	private void getLocations(Player player) {
		
		player.sendMessage(ChatColor.YELLOW + "Waypoints: " + ChatColor.AQUA + this.plugin.getLocationManager().getLocationList());
		
	}
	
	private void deleteLocation(Player player, String name) {
		
		if(this.plugin.getLocationManager().deleteLocation(name)) {
			
			player.sendMessage(ChatColor.RED + "Location '" + name + "' deleted!");
			
		} else {
			
			player.sendMessage(ChatColor.RED + "Location could not be deleted!");
			
		}
		
	}
	
}
