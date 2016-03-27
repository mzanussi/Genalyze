package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * T-Server header state. Implements marker interface, <code>Header</code>.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class TServerHeaderState extends AbstractTServerState implements Header {
	
	// Prevent attributes and subattributes at the start of the
	// first scanned file from triggering a false positive on 
	// isAttribute() and isSubattribute() in TServerMessageState. 
	// Rather, treat as part of header and ignore.
	private boolean firstScan = true;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerHeaderState(Genesys log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isEmpty(line)) {
			// Need to check for end of header, required for multi-file processing.
			//log.dump(line, "skipping");
			// CHANGE STATE
			if (firstScan) {
				log.getStateMachine().setState(log.getStateMachine().getTServerPostHeaderState());
				firstScan = false;
			}
			else {
				log.getStateMachine().setState(log.getStateMachine().getTServerMessageState());
			}
		}
		else if (isMessage(line)) {
			throw new StateException("Message encountered waiting for end of header.");
		}
		else if (isAttribute(line)) {
			throw new StateException("Attribute encountered waiting for end of header.");
		}
		else if (isSubattribute(line)) {
			throw new StateException("Subattribute encountered waiting for end of header.");
		}
		else {
			//log.dump(line, "skipping");
		}
	}

}
