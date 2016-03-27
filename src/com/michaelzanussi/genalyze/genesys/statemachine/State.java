package com.michaelzanussi.genalyze.genesys.statemachine;


/**
 * This interface defines a Genesys log state. It provides for a single
 * method, <code>exec()</code>, which executes the current state on
 * the passed line of log input.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public interface State {

	/**
	 * Execute the current state.
	 * 
	 * @param line the current line of log input.
	 */
	public void exec(String line) throws StateException;
	
}
