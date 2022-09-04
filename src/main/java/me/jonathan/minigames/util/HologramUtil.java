/**
 * 
 */
package me.jonathan.minigames.util;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import com.github.unldenis.hologram.Hologram;
import com.github.unldenis.hologram.HologramPool;
import com.github.unldenis.hologram.TextLine;
import com.github.unldenis.hologram.animation.Animation;
import com.github.unldenis.hologram.event.PlayerHologramHideEvent;
import com.github.unldenis.hologram.event.PlayerHologramInteractEvent;
import com.github.unldenis.hologram.event.PlayerHologramShowEvent;
import com.github.unldenis.hologram.line.ItemLine;

/**
 * @author jonah
 * 
 */
@SuppressWarnings({"unused", "serial"})
public class HologramUtil implements Listener {

    private final Plugin plugin;
    public final HologramPool hologramPool;
    /**
     * @param plugin The plugin which uses the lib
     */
    public HologramUtil(@NotNull Plugin plugin) {
        this.plugin = plugin;
        this.hologramPool = new HologramPool(plugin, 70, 0.5f, 5f);
    }

    /**
     * Appends a new Hologram to the pool.
     *
     * @param location  The location the Hologram will be spawned at
     * @param excludedPlayer A player which will not see the Hologram for 10 seconds
     */
    public void appendHOLO(@NotNull Location location, Player excludedPlayer) {
        // building the NPC
        Hologram hologram = Hologram.builder()
                .location(location)
                .addLine("Hello World!", false)
                .addLine("Using Hologram-Lib", false)
                .addLine("Hello %%player%%", true)
                .addLine(new ItemStack(Material.IRON_BLOCK))
                .addPlaceholder("%%player%%", Player::getName)
                .build(hologramPool);

        hologram.getLines().get(3).setAnimation(Animation.AnimationType.CIRCLE);
        // simple changing animating block and text
        timingBlock(hologram);

        if (excludedPlayer != null) {
            // adding the excluded player which will not see the Hologram
            hologram.addExcludedPlayer(excludedPlayer);

            // shows 10 seconds after theHologram
            Bukkit.getScheduler().runTaskLater(plugin, () -> hologram.removeExcludedPlayer(excludedPlayer), 20L * 10);
        }
    }

    
	private final static Queue<Material> materials = new ArrayDeque<Material>(){
        {
            addAll(Arrays.asList( Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.EMERALD_BLOCK));
        }
    };


    /**
     * Update the block and the first line of text of the hologram
     * @param hologram The hologram to update
     */
    private void timingBlock(Hologram hologram) {
        new BukkitRunnable() {
            final ItemLine itemLine = (ItemLine) hologram.getLines().get(3);
            @Override
            public void run() {
                Material mat = materials.poll();
                itemLine.set(new ItemStack(mat));
                materials.offer(mat);
            }
        }
        .runTaskTimer(plugin, 30L, 30L);
    }

    /**
     * Doing something when a Hologram is shown for a certain player.
     * @param event The event instance
     */
    @EventHandler
    public void onHologramShow(PlayerHologramShowEvent event) {
        Hologram holo = event.getHologram();
        Player player = event.getPlayer();
    }

    /**
     * Doing something when a Hologram is hidden for a certain player.
     * @param event The event instance
     */
    @EventHandler
    public void onHologramHide(PlayerHologramHideEvent event) {
        Hologram holo = event.getHologram();
        Player player = event.getPlayer();
    }

    /**
     * Doing something when a Hologram is left-clicked by a certain player.
     * @param e The event instance
     */
    @EventHandler
    public void onHologramInteract(PlayerHologramInteractEvent e) {
        Player player = e.getPlayer();
        TextLine line = e.getLine();
        player.sendMessage("Click at " + line.parse(player));
    }
    public static void create(@NotNull Location location, Player excludedPlayer) {
    }
}