/**
 * 
 */
package me.jonathan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;

import me.jonathan.events.Events;
import me.jonathan.minigames.commands.Join;
import me.jonathan.minigames.commands.Reload;

/**
 * @author jonah
 * 
 */
public class Gen extends JavaPlugin{
    private FileConfiguration newConfig = null;
    private File ctConfigFile;
    private FileConfiguration ctConfig;
    //private File oldConfigFile;
    //private FileConfiguration oldConfig;
    //private File oldConfigFile;
    //private FileConfiguration oldConfig;
    //private File oldConfigFile;
    //private FileConfiguration oldConfig;
	
	private static Gen instance;
	@Override
	public void onLoad() {
		instance = this;
		createCTConfig();
		saveDefaultConfig();
		
	}
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		getCommand("minigames").setExecutor(new Join());
		getCommand("genreload").setExecutor(new Reload());
		reloadConfig();
		getServer().getPluginManager().registerEvents(new me.jonathan.minigames.events.Events(), this);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Genesis network] Plugin Has Been Enabled!");
	}
	@Override
	public void onDisable() {
		saveDefaultConfig();
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Genesis network] Plugin Has Been Disabled!");
	}
	public static Gen getInstance() {
		return instance;
	}
	
	
	public FileConfiguration getCTConfig() {
        return this.ctConfig;
    }
	private void createCTConfig() {
		ctConfigFile = new File(getDataFolder(), "minigames/Color-Takeover.yml");
        if (!ctConfigFile.exists()) {
        	ctConfigFile.getParentFile().mkdirs();
            saveResource("minigames/Color-Takeover.yml", false);
        }
        
        ctConfig = new YamlConfiguration();
        try {
        	ctConfig.load(ctConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
	}
   
	public void reloadCTConfig() {
        newConfig = YamlConfiguration.loadConfiguration(ctConfigFile);

        final InputStream defConfigStream = getResource("minigames/Color-Takeover.yml");
        if (defConfigStream == null) {
            return;
        }

        newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }
	
}
