/**
 * 
 */
package me.jonathan.minigames.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import me.jonathan.Gen;
import me.jonathan.minigames.main.Main;

/**
 * @author jonah
 * 
 */
public class MatchUtil {

	private static List<Integer> match = new ArrayList<Integer>();
	
	public static void openmatch() {
		matches();
		for (int i = 0; i < match.size(); i++) {
			if (Main.CTclosedmaps.contains(match.get(i)) && !Main.CTclosedmaps.contains(i)) {
				match.remove(i);
			} else if (!Main.CTopenmaps.contains(i)){
				Main.CTopenmaps.add(i);
			}
			
		}
	}
	private static void matches() {
		FileConfiguration config = Gen.getInstance().getCTConfig();
		match.clear();
		int e = 0;
		while (config.get(String.valueOf(e)) != null) {
			match.add(e);
			e+=1;
		}
		
	}
	public static boolean startMatch(Integer gamenumber) {
		
		if (Main.CTclosedmaps.contains(gamenumber)) {
			Main.CTclosedmaps.add(gamenumber);
			return true;
		}
		
		return false;
	}
	
	
	public static void sendError(Error error) {
		int code=Error.getCode(error);
		String msg=Error.getErrorMessage(error);
		Bukkit.getLogger().warning("ERROR CODE: " + code + " Response: " + msg);
	}
	
}
