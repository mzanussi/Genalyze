package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * This class provides a skeletal implementation of the <code>State</code> 
 * interface, to minimize the effort required to implement this interface.
 * This abstract class provides support for Genesys T-Server logs and extends
 * the standard parser, <code>GenesysParser</code>, overriding where necessary 
 * for specific T-Server functionality.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public abstract class AbstractTServerState extends GenesysParser implements State {
	
	protected Genesys log;		// the log

	/**
	 * Is this a T-Server event? 
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isTServerEvent(String line) {
		// Events start with 'message Event' and start at position > 0.
		// Check for empty line first to prevent index out of bounds.
		if (!isEmpty(line) && line.charAt(0) == '@' && Character.isDigit(line.charAt(1))) {
			if (line.indexOf("message Event") != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Is this a T-Server request? 
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isTServerRequest(String line) {
		// Requests start with 'message Request' and start at position 0.
		if (line.indexOf("message Request") == 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.GenesysParser#isMessage(java.lang.String)
	 */
	public boolean isMessage(String line) {
		return (isTServerEvent(line) || isTServerRequest(line));
	}

	/**
	 * Is this the end of a T-Server message? 
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line ends a message; otherwise, <code>false</code>.
	 */
	public boolean isTServerEOM(String line) {
		// Messages end on empty line or a line with
		// non-whitespace at position 0.
		if (isEmpty(line)) {
			return true;
		}
		if (!Character.isWhitespace(line.charAt(0))) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.GenesysParser#isEOM(java.lang.String)
	 */
	public boolean isEOM(String line) {
		return isTServerEOM(line);
	}
	
}
