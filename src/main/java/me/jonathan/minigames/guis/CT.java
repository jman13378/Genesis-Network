/**
 * 
 */
package me.jonathan.minigames.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.jonathan.Gen;
import me.jonathan.minigames.main.Main;
import me.jonathan.minigames.util.MatchUtil;
import net.kyori.adventure.text.Component;

/**
 * @author jonah
 * 
 */
public class CT {

	//// if this doesn't get used i wasted 1 and a half hours working on this
	public static void openTCmenu(Player player) {
		MatchUtil.getMatchCount();
		FileConfiguration config = Gen.getInstance().getConfig();
		FileConfiguration mconfig = Gen.getInstance().getCTConfig();
		Gui gui = Gui.gui()
				.title(Component.text(config.getString("gui.title")))
				.rows(3)
				.disableAllInteractions()
				.create();
		for (int i = 0; i < Main.CTopenmaps.size(); i++) {
			List<Component> lore = new ArrayList<Component>();
			lore.add(Component.text("Left Click: Click to join this game."));
			lore.add(Component.text("Max Players: " + mconfig.getInt(Main.CTopenmaps.get(i) + ".max-players")));
			lore.add(Component.text("Min Players: " + mconfig.getInt(Main.CTopenmaps.get(i) + ".min-players")));
			lore.add(Component.text("Time Limit: " + mconfig.getInt(Main.CTopenmaps.get(i) + ".timelimit")));
			GuiItem item = ItemBuilder.from(Material.getMaterial(mconfig.getString(Main.CTopenmaps.get(i) + ".gui.open")))
					.name(Component.text("Game: " +  Main.CTopenmaps.get(i)))
					.lore(lore)
					.asGuiItem(event -> {
						if (!event.isLeftClick()) return;
						String game = event.getCurrentItem().getItemMeta().getDisplayName().replace("Game: ", "");
						Player p = (Player) event.getWhoClicked();
						p.sendMessage("sending you to Color Takeover...");
						Bukkit.broadcastMessage(p.getDisplayName() + "Has joined Color-Takeover");
						new BukkitRunnable() {
							@Override
							public void run() {
								World env = Bukkit.getWorld(mconfig.getString(game +".world"));
								Location loc = new Location(env, mconfig.getDouble(game +".xCord"),mconfig.getDouble(game +".yCord"), mconfig.getDouble(game +".zCord"));
								p.teleport(loc);
								Main.CT.put(p, game);
							}
						}.runTaskLater(Gen.getInstance(), 60L);
						
						
					});
			gui.addItem(item);
		}
		for (int i = 0; i < Main.CTclosedmaps.size(); i++) {
			List<Component> lore = new ArrayList<Component>();
			lore.add(Component.text("Max Players: " + mconfig.getInt(Main.CTopenmaps.get(i) + ".max-players")));
			lore.add(Component.text("Min Players: " + mconfig.getInt(Main.CTopenmaps.get(i) + ".min-players")));
			lore.add(Component.text("Time Limit: " + mconfig.getInt(Main.CTopenmaps.get(i) + ".timelimit")));
			GuiItem item = ItemBuilder.from(Material.getMaterial(mconfig.getString(Main.CTclosedmaps.get(i) + ".closed")))
					.name(Component.text("Game: " +Main.CTclosedmaps.get(i) + "In Session"))
					.lore(lore)
					.asGuiItem();
			gui.addItem(item);
		}
		gui.open(player);
	}
	
}
