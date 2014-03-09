package in.sandboxed.waypointCompass;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fusesource.jansi.Ansi.Color;

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
			return false;
			
		}
		
		switch(args[0]) {
		case "mark": 
			
			if(args.length==1) {
				
				sender.sendMessage(ChatColor.RED + "ERROR: Did you name the location and specify privacy? (/wc mark locationName private)");
				break;
				
			}
						
			if(args.length==3 && args[2].equals("private"))
				this.markLocation((Player) sender, args[1], ((Player) sender).getLocation(), true);
			else
				this.markLocation((Player) sender, args[1], ((Player) sender).getLocation(), false);
			
			break;
		
		case "goto": 
		
			if(args.length<1) {
				
				sender.sendMessage(ChatColor.RED + "ERROR: Did you specify the location? (/wc goto locationName)");
				break;
				
			}
			
			this.pointToLocation((Player) sender, args[1]);
			break;
		
		case "list": 
			
			this.getLocations((Player) sender);
			break;
		
		case "delete": 
		
			this.deleteLocation((Player) sender, args[1]);
			break;
			
		case "broadcast":
			
			this.broadcast(((Player) sender));
			break;
			
		default:
			
			// Invalid command
			return false;
	
		}
		
		
		return true;
	}
	
	private void broadcast(Player player) {
		
		Location playerLocation = player.getLocation();
		int x = playerLocation.getBlockX();
		// Add 1 since getBlock gets the block at player's feet
		int y = playerLocation.getBlockY() + 1;
		int z = playerLocation.getBlockZ();
		
		this.plugin.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " is at " + x + ", " + y + ", " + z);
		
	}
	
	private void markLocation(Player player, String name, Location location, boolean isPrivate) {
		
		
		if(isPrivate) {

			this.plugin.getWaypointManager().addWaypoint(name, player.getName(), location, true);
			player.sendMessage(ChatColor.YELLOW + "Waypoint '" + name + "' saved! This waypoint is private.");
		
		} else {
			
			this.plugin.getWaypointManager().addWaypoint(name, player.getName(), location, false);
			player.sendMessage(ChatColor.YELLOW + "Waypoint '" + name + "' saved! This waypoint is public.");
			
		}
				
	}
	
	private void pointToLocation(Player player, String name) {
		
		Waypoint waypoint;
		
		if(!this.plugin.getWaypointManager().containsWaypoint(name)) {
			
			player.sendMessage(ChatColor.RED + "Waypoint '" + name + "' does not exist.");
			return;
			
		}
		
		waypoint = this.plugin.getWaypointManager().getWaypoint(name);
		
		if(!waypoint.isPrivate()) {
			
			player.setCompassTarget(waypoint.getLocation());
			player.sendMessage(ChatColor.YELLOW + "Waypoint '" + name + "' set! Use a compass to guide you to the location.");
			
		} else if(waypoint.getPlayer().equals(player.getName())) {
			
			player.setCompassTarget(waypoint.getLocation());
			player.sendMessage(ChatColor.YELLOW + "Waypoint '" + name + "' set! Use a compass to guide you to the location.");
			
		} else {
			
			player.sendMessage(ChatColor.RED + "ERROR: Waypoint '" + name + "' is private.");
			
		}
		
		
	}
	
	private void getLocations(Player player) {
		
		String list = "";
		
		for(String key : this.plugin.getWaypointManager().getWaypointsForPlayer(player.getName()).keySet()) {
			
			Waypoint current = this.plugin.getWaypointManager().getWaypoint(key);
			list += "\n  - " + key + " (" + current.getLocation().getBlockX() + ", " + current.getLocation().getBlockY() + ", " + current.getLocation().getBlockZ() + ")";
			
		}
		
		player.sendMessage(ChatColor.YELLOW + "Waypoints: " + ChatColor.AQUA + list);
		
	}
	
	private void deleteLocation(Player player, String name) {
		
		if(this.plugin.getWaypointManager().removeWaypoint(name, player.getName())) {
			
			player.sendMessage(ChatColor.YELLOW + "Location '" + name + "' deleted!");
			
		} else {
			
			player.sendMessage(ChatColor.RED + "ERROR: Location could not be deleted!");
			
		}
		
	}
	
}
