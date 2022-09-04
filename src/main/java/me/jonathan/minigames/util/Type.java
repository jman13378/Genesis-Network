/**
 * 
 */
package me.jonathan.minigames.util;

/**
 * @author jonah
 * 
 */
public enum Type {
	
	t1V1("Red"),
	t2V2("Blue"),
	t3V3("Green"),
	t4V4("Yellow");
	
    private final String type;
    
    Type(String type) {this.type = type;}
	/**
	 * 
	 * @return Color the color from the Bukkit enum
	 */
	public String getType() {return this.type;}
/*	/**
*	 * 
*	 * @return String the text form of the color
*	 *
*	public String getSColor() {
*		return this.scolor;
*	}
*/
}
