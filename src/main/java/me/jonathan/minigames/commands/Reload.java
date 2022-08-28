/**
 * 
 */
package me.jonathan.minigames.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import me.jonathan.Gen;

/**
 * @author jonah
 * 
 */
public class Reload implements TabExecutor {

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("genreload")) {
			
			if (args[0].equals("ALL")) {
				Gen.getInstance().reloadConfig();
				Gen.getInstance().reloadCTConfig();
				Bukkit.getLogger().info("All config have been reloaded");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Reload complete!"));
			} else
			if (args[0].equals("NORMAL")) {
				Gen.getInstance().reloadConfig();
				Bukkit.getLogger().info("The normal config has been reloaded");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Reload complete!"));
			} else
			if (args[0].equals("COLOR-TAKEOVER")) {
				Gen.getInstance().reloadCTConfig();
				Bukkit.getLogger().info("The Color-Takeover config has been reloaded");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Reload complete!"));
			} else {
				sender.sendMessage("Please choose one of the options [ALL|NORMAL|COLOR-TAKEOVER");
			}
			
			
		}
		return true;
	}

	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length==1) {
			completions.add("ALL");
			completions.add("NORMAL");
			completions.add("COLOR-TAKEOVER");
			return completions;
		}
		return null;
	}
}
