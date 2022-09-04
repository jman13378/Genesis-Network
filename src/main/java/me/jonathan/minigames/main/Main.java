/**
 * 
 */
package me.jonathan.minigames.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import me.jonathan.Triplet;
import me.jonathan.minigames.util.Team;
import me.jonathan.minigames.util.Type;

/**
 * @author jonah
 * 
 */ 
public class Main {
	
	/**
	 * color takeover
	 * 
	 * @param Player the player to be added
	 * @param String the match the player is entering
	 * 
	 */
	public static List<Triplet<Player, Team, Type>> team = new ArrayList<>();
	public static HashMap<Player, String> CT = new HashMap<Player, String>();
	public static List<Integer> CTclosedmaps = new ArrayList<Integer>();
	public static List<Integer> CTopenmaps = new ArrayList<Integer>();
	public static List<Player> CTInqueue = new ArrayList<Player>();
	
	
	
	public static String team(Player player) {
		Triplet<Player, Team, Type> p = team.stream().filter(r ->  r.getKey().equals(player)).findFirst().orElse(null);
		return p.getValue().getSColor();
	}
}
