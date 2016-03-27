package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * A state machine for Genesys logs (as state pattern). 
 *  
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class GenesysStateMachine {

	private State initState;
	private State headerState; 
	private State tserverHeaderState;
	private State tserverPostHeaderState;
	private State tserverSearchState;
	private State tserverMessageState;
	private State tserverAttributeState;
	private State tserverSubattributeState;
	private State tserverUserDataState;

	private State state;				// the current state
	private State laststate;			// the last state
	private State lastknownheaderstate;	// the header state (type being processed)

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public GenesysStateMachine(Genesys log) {
		initState = new InitState(log);
		headerState = new HeaderState(log);
		tserverHeaderState = new TServerHeaderState(log);
		tserverPostHeaderState = new TServerPostHeaderState(log);
		tserverSearchState = new TServerSearchState(log);
		tserverMessageState = new TServerMessageState(log);
		tserverAttributeState = new TServerAttributeState(log);
		tserverSubattributeState = new TServerSubattributeState(log);
		tserverUserDataState = new TServerUserDataState(log);
		laststate = null;
		lastknownheaderstate = null;
		state = initState;		// initial state
	}
	
	/**
	 * Returns the initial state.
	 * 
	 * @return the initial state.
	 */
	public State getInitState() {
		return initState;
	}

	/**
	 * Returns the header state.
	 * 
	 * @return the header state.
	 */
	public State getHeaderState() {
		return headerState;
	}

	/**
	 * Returns the T-Server header state.
	 * 
	 * @return the T-Server header state.
	 */
	public State getTServerHeaderState() {
		return tserverHeaderState;
	}

	/**
	 * Returns the T-Server post-header state.
	 * 
	 * @return the T-Server post-header state.
	 */
	public State getTServerPostHeaderState() {
		return tserverPostHeaderState;
	}

	/**
	 * Returns the searching for T-Server message state.
	 * 
	 * @return the searching for T-Server message state.
	 */
	public State getTServerSearchState() {
		return tserverSearchState;
	}

	/**
	 * Returns the T-Server message state.
	 * 
	 * @return the T-Server message state.
	 */
	public State getTServerMessageState() {
		return tserverMessageState;
	}

	/**
	 * Returns the T-Server attribute state.
	 * 
	 * @return the T-Server attribute state.
	 */
	public State getTServerAttributeState() {
		return tserverAttributeState;
	}
	
	/**
	 * Returns the T-Server subattribute state.
	 * 
	 * @return the T-Server subattribute state.
	 */
	public State getTServerSubattributeState() {
		return tserverSubattributeState;
	}
	
	/**
	 * Returns the T-Server user data state.
	 * 
	 * @return the T-Server user data state.
	 */
	public State getTServerUserDataState() {
		return tserverUserDataState;
	}
	
	/**
	 * Returns the current state.
	 * 
	 * @return the current state.
	 */
	public State getState() {
		return state;
	}

	/**
	 * Set the current state.
	 * 
	 * @param state the state to set the current state to.
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * Returns the last state the machine was in.
	 * Used when spanning multiple log files.
	 * 
	 * @return the last state the machine was in.
	 */
	public State getLastState() {
		return laststate;
	}

	/**
	 * Set the last state that the machine was in.
	 * Used when spanning multiple log files.
	 * 
	 * @param laststate the state to set the last state to.
	 */
	public void setLastState(State laststate) {
		this.laststate = laststate;
	}

	/**
	 * Set the header state, indicates the type of log being processed.
	 * 
	 * @param state the header state.
	 */
	public void setLastKnownHeaderState(State lastknownheaderstate) {
		this.lastknownheaderstate = lastknownheaderstate;
	}
	
	/**
	 * Returns the header state, indicates the type of log being processed.
	 * Used when spanning multiple log files.
	 * 
	 * @return the header state the machine is in.
	 */
	public State getLastKnownHeaderState() {
		return lastknownheaderstate;
	}

}
