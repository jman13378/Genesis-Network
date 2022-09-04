/**
 * 
 */
package me.jonathan.minigames.states;

import java.time.Duration;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author jonah
 * 
 */
public class PregameState extends GameState {
    public PregameState(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    protected void onStart() {
        broadcast("Now accepting players!");
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
 
    }

    @Override
    public void onUpdate() {
 
    }

    @Override
    protected void onEnd() {
 
    }

    @Override
    public boolean isReadyToEnd() {
        return getPlayers().size() > 12; // you can implement a countdown by creating a state!
    }

    @NotNull
    @Override
    public Duration getDuration() {
        return Duration.ZERO;
    }
}