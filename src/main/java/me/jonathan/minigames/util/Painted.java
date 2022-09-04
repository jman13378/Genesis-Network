/**
 * 
 */
package me.jonathan.minigames.util;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author jonah
 * 
 */
@SuppressWarnings("unused")
public class Painted implements Listener{

	
	@EventHandler
	public static void onInteract(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if (MatchUtil.isInGame(player)) {
			
		} else return;
	}
	@EventHandler
	public static void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (MatchUtil.isInGame(player)) {
			event.setCancelled(true);
		} else return;
	}
}
