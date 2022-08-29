/**
 * 
 */
package me.jonathan.minigames.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.jonathan.Gen;
import me.jonathan.minigames.main.Main;
import me.rockyhawk.commandpanels.api.Panel;
import me.rockyhawk.commandpanels.api.PanelCommandEvent;

/**
 * @author jonah
 * 
 */
public class Events implements Listener {

	@EventHandler
	public static void onWalk(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (Main.CT.containsKey(player)) {
				
		}
	}
	
	@EventHandler
	public static void cpevent(PanelCommandEvent event) {
		
		String rawMsg = event.getMessage();
		FileConfiguration config = Gen.getInstance().getConfig();
		if (!config.getBoolean("gui.cp-override.enabled")) return;
		Panel panel = event.getPanel();
		String panelname = panel.getName();
		if (rawMsg.startsWith(config.getString("gui.cp-override.identifier"))) 
			if (panelname.equalsIgnoreCase(config.getString("gui.cp-override.panelname")))
				
			{
				String[] args = rawMsg.split("\\s+");
				String gamenumber = args[0];
				Player player = Bukkit.getPlayer(args[1]);
				
				
				
		}
	}

}
