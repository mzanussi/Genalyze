package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * This class provides a skeletal implementation of the <code>State</code> 
 * interface, to minimize the effort required to implement this interface.
 * This generic abstract class provides support for all types of Genesys
 * logs and extends the standard parser, <code>GenesysParser</code>.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public abstract class AbstractState extends GenesysParser implements State {

	protected Genesys log;		// the log

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.GenesysParser#isHeader(java.lang.String)
	 */
	public boolean isHeader(String line) {
		return (isTServerHeader(line));
	}

	/**
	 * Is this the start of a T-Server log?
	 * 
	 * @param line the current log line being processed
	 * @return <code>true</code> if line begins a message; otherwise, <code>false</code>.
	 */
	public boolean isTServerHeader(String line) {
		// Check for empty line.
		if (isEmpty(line)) {
			return false;
		}
		if (line.indexOf("Application name: TServer") == 0) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.GenesysParser#isMessage(java.lang.String)
	 */
	public boolean isMessage(String line) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.GenesysParser#isEOM(java.lang.String)
	 */
	public boolean isEOM(String line) {
		return false;
	}
	
}
