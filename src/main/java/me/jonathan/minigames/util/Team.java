/**
 * 
 */
package me.jonathan.minigames.util;

import org.bukkit.Color;

/**
 * @author jonah
 * 
 */
public enum Team {

	
	
	RED(Color.RED, "Red"),
	BLUE(Color.BLUE,"Blue"),
	GREEN(Color.GREEN,"Green"),
	YELLOW(Color.YELLOW,"Yellow");
	
    private final Color ccolor; // in meters
    private final String scolor;
    Team(Color ccolor, String scolor) {
        this.ccolor = ccolor;
        this.scolor = scolor;
	}
	/**
	 * 
	 * @return Color the color from the Bukkit enum
	 */
	public Color getCColor() {
		return this.ccolor;
	}
	/**
	 * 
	 * @return String the text form of the color
	 */
	public String getSColor() {
		return this.scolor;
	}
}
