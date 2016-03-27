package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * Sets the all important last known header state so on multi-file 
 * processing we can jump right to the header processing we need.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class HeaderState extends AbstractState {

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public HeaderState(Genesys log) {
		this.log = log;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isTServerHeader(line)) {
			//log.dump(line, "T-SERVER");
			log.getStateMachine().setLastKnownHeaderState(log.getStateMachine().getTServerHeaderState());
			log.getStateMachine().setState(log.getStateMachine().getTServerHeaderState());
    	}
		else if (isMessage(line)) {
			throw new StateException("Message encountered looking for header."); 
		}
		else if (isAttribute(line)) {
			throw new StateException("Attribute encountered looking for header.");
		}
		else if (isSubattribute(line)) {
			throw new StateException("Subattribute encountered looking for header.");
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
