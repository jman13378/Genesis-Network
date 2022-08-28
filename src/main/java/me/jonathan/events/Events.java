/**
 * 
 */
package me.jonathan.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author jonah
 * 
 */
public class Events implements Listener {

	
	@EventHandler
	public static void onjoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
	}
	
}
