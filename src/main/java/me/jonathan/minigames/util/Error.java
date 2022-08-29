/**
 * 
 */
package me.jonathan.minigames.util;

/**
 * @author jonah
 * 
 */
public enum Error {

	
	MATCHEXISTS("A match already exists",1),
	MATCHOPEN("The player couldnt connect to the match but the match was open",2);

	Error(String message, int code) {
	}
	
	static int getCode(Error error) {
		Error[] args = Error.values();
		return Integer.getInteger(args[1].toString());
	}
	static String getErrorMessage(Error error) {
		Error[] args = Error.values();
		return args[0].toString();
	}
	
	
}
