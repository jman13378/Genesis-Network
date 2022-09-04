/**
 * 
 */
package me.jonathan.minigames.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.jonathan.Gen;
import me.jonathan.minigames.main.Main;

/**
 * @author jonah
 * 
 */
public class MatchUtil {

	
	public static void getMatchCount() {
		FileConfiguration config = Gen.getInstance().getCTConfig();
		int e = 1;
		while (config.contains(String.valueOf(e))) {
			if (!Main.CTclosedmaps.contains(e) && !Main.CTclosedmaps.contains(e)) {
				Main.CTopenmaps.add(e);
				e++;
			} else {
				sendError(Error.MATCHOPEN);
			}
		}
	}
	
	public static boolean isInGame(Player p) {
		return Main.CT.containsKey(p);
	}
	public static void sendError(Error error) {
		String msg = error.getErrorMessage();
		int code = error.getCode();
		Error.MATCHEXISTS.getErrorMessage();
		Bukkit.getLogger().warning("ERROR CODE: " + code + " Response: " + msg);
	}
	
}
