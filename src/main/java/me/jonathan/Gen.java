/**
 * 
 */
package me.jonathan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;

import me.jonathan.database.Database;
import me.jonathan.events.Events;
import me.jonathan.minigames.commands.Join;
import me.jonathan.minigames.commands.Reload;
import me.jonathan.minigames.util.Error;
import me.jonathan.minigames.util.HologramUtil;
import me.jonathan.minigames.util.MatchUtil;

/**
 * @author jonah
 * 
 */
public class Gen extends JavaPlugin{
	
	
	
	private static final boolean enableDatabase = true;
    /**
     * the database type
     *
     * @apiNote the types are MySQL and SQLite
     */
    private static final Database.DBType dbType = Database.DBType.SQLite;
    /**
     * the database
     */
    public static Database database = null;
	
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
		Bukkit.getLogger().info("Attempting to connect to the database");
        if (enableDatabase) database = new Database(dbType);
        if (database != null) {
        	if (!database.isConnected()) {
                database = null;
                Bukkit.getLogger().severe("[ERROR] Database connection failed. Continuing without database.");
            } else {
            	//
            	database.update("CREATE TABLE IF NOT EXISTS wins (playeruuid VARCHAR(36) NOT NULL,"
            			+ " wins VARCHAR(8) NOT NULL);");
            }
        }
		
	}
	@Override
	public void onEnable() {
		if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
			Bukkit.getLogger().severe("You need to install ProtocolLib @ https://www.spigotmc.org/resources/protocollib.1997/ for the plugin to function");
			Bukkit.getPluginManager().disablePlugin(this);
		}

		MatchUtil.getMatchCount();
		getServer().getPluginManager().registerEvents(new Events(), this);
		getServer().getPluginManager().registerEvents(new HologramUtil(this), this);
		getCommand("minigames").setExecutor(new Join());
		getCommand("genreload").setExecutor(new Reload());
		reloadConfig();
		getServer().getPluginManager().registerEvents(new me.jonathan.minigames.events.Events(), this);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Genesis network] Plugin Has Been Enabled!");
		MatchUtil.sendError(Error.MATCHEXISTS);
	}
	@Override
	public void onDisable() {
		if (database != null) {
			getLogger().info("Disconnecting database");
			database.disconnect();
		}
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
