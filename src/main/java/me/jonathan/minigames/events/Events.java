/**
 * 
 */
package me.jonathan.minigames.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.jonathan.minigames.main.Main;

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
}
