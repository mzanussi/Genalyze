package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * Initial state.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class InitState extends AbstractState {
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public InitState(Genesys log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isHeader(line)) {
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getHeaderState());
			// Pushback current line.
			log.setPushback(true);
		}
		else {
			//log.dump(line, "skipping");
		}
	}

}
