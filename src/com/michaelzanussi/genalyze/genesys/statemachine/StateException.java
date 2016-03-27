package com.michaelzanussi.genalyze.genesys.statemachine;

/**
 * An state exception typically indicates an unsupported feature is being
 * run.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class StateException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Standard constructor.
	 * 
	 * @param message the exception message.
	 */
	public StateException(String message) {
		super(message);
	}
	
}
