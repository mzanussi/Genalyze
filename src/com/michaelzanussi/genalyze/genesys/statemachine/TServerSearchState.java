package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * T-Server search state. This state occurs whenever searching
 * for a new message.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (6 January 2009) 
 */
public class TServerSearchState extends AbstractTServerState {

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerSearchState(Genesys log) {
		this.log = log;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isMessage(line)) {
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerMessageState());
			// Pushback current line.
			log.setPushback(true);
    	}
    	else {
    		// Some other kind of log entry, typically messages
    		// originating from the switch or message contents
    		// that we can ignore because we're not currently 
    		// processing a message.
    		//log.dump(line, "skipping");
    	}
	}

}
