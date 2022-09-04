/**
 * 
 */
package me.jonathan.minigames.util;

/**
 * @author jonah
 * 
 */
public enum Error {

	/**
	 * Message - A match already exists
	 * <p>Code - 1</p>
	 */
	MATCHEXISTS("A match already exists",1),
	/**
	 * Message - The player couldn't connect to the match but the match was open
	 * <p>
	 * Code - 2
	 * </p>
	 */
	MATCHOPEN("The player couldn't connect to the match but the match was open",2);
    private final String message;   // in kilograms
    private final int code; // in meters
	Error(String message, int code) {
        this.message = message;
        this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	public String getErrorMessage() {
		return this.message;
	}
		
}
