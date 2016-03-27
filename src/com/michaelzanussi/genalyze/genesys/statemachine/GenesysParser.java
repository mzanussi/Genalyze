package com.michaelzanussi.genalyze.genesys.statemachine;

/**
 * A generic Genesys parser. Provides general support for determining
 * if a line of input is empty, the start of a log, a message, an 
 * attribute or a subattribute. 
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public abstract class GenesysParser {

	/**
	 * Is this an empty line?
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isEmpty(String line) {
		if ("".equals(line)) {
			return true;
		}
		return false;
	}

	/**
	 * Is this the start of the header?
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isHeader(String line) {
		if (line.indexOf("Application name: ") == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Is this a message? 
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public abstract boolean isMessage(String line);
	
	/**
	 * Is this an attribute?
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isAttribute(String line) {
		// Check for empty line.
		if (isEmpty(line)) {
			return false;
		}
		if (Character.isWhitespace(line.charAt(0)) && line.indexOf("Attribute") != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Is this a subattribute? (also known as a list attribute)
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isSubattribute(String line) {
		// Check for empty line.
		if (isEmpty(line)) {
			return false;
		}
		if ((Character.isWhitespace(line.charAt(0)) &&
				Character.isWhitespace(line.charAt(1)) &&
				line.charAt(2) == '\'') || line.indexOf("  '") != -1) {
			return true;
		}
			
		return false;
	}

	/**
	 * Is this the end of a message? 
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line ends a message; otherwise, <code>false</code>.
	 */
	public abstract boolean isEOM(String line);
	
}
