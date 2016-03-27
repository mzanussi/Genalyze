package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * T-Server post-header state. This state occurs only once during 
 * log processing: between the header and the start of the first 
 * message.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (30 December 2008) 
 */
public class TServerPostHeaderState extends AbstractTServerState implements Header {

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerPostHeaderState(Genesys log) {
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
		else if (isHeader(line)) {
			throw new StateException("Header encountered looking for message.");
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
