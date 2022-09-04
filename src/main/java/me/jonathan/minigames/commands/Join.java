/**
 * 
 */
package me.jonathan.minigames.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.jonathan.Gen;
import me.jonathan.minigames.guis.CT;
import me.jonathan.minigames.states.PregameState;

/**
 * @author jonah
 * 
 */

// Seby is not smart
public class Join implements TabExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage("Sorry this cannot be executed from console!");
		FileConfiguration config = Gen.getInstance().getConfig();
		Player player = (Player) sender;
		PregameState state = new PregameState(Gen.getInstance());
		state.start();
		state.end();
		if (args[0].equals("join")) {
			if (args[1].equals("Color-Takeover")) {
				if (config.getBoolean("gui.enabled")) {CT.openTCmenu(player);return true;}
				// what i have above this will solve it all this is a waste of code the statements under this
				
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			completions.add("join");
			return completions;
		}
    	if (args.length == 2){
    		FileConfiguration config = Gen.getInstance().getConfig();
    		
    		
    		if (config.getBoolean("minigames.color-takeover.enabled")) completions.add("Color-Takeover");
    		//if (config.getBoolean("minigames.color-takeover.enabled")) completions.add("Color-Takeover");
    		//if (config.getBoolean("minigames.color-takeover.enabled")) completions.add("Color-Takeover");
    		//if (config.getBoolean("minigames.color-takeover.enabled")) completions.add("Color-Takeover");
    		return completions;
    	}
    	return null;
	}

}
