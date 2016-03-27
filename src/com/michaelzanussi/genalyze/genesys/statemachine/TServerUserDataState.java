package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;
import com.michaelzanussi.genalyze.property.GenalyzePropertyManager;
import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * T-Server userdata state.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class TServerUserDataState extends AbstractTServerState {

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerUserDataState(Genesys log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isSubattribute(line)) {
			// Output the line to console.
			//log.dump(line, "    user");

			// Generate a key-value pair containing the userdata and value.
			KeyValuePair kvp = new KeyValuePair(line);
			
			// Check to see if user data should be stored or not.
			// If so, add it to the message. Otherwise, handle the
			// key-value pair below.
			if (GenalyzePropertyManager.getInstance().isStoreUserData()) {
				log.getAttribute().addListAttribute(kvp);
			}

			// ***********************************************************
			// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
			// ***********************************************************
			
			// MBZ: Add checks for non-standard Genesys items, such
			// as key-value pairs in userdata. 
			if ("EWT".equals(kvp.getKey())) {
				log.getMessage().setEWT(kvp.getValue());
			}
			log.setCustomData(kvp);

			// ***********************************************************
			// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA] 
			// ***********************************************************
			
		}
		else if (isAttribute(line)) {
			// Save the attribute.
			log.updateMessage();
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerAttributeState());
			// Pushback current line.
			log.setPushback(true);
		}
		else if (isMessage(line)) {
			// Save the attribute to the message.
			log.updateMessage();
			// This new message ends the old message. Update call.
			log.updateCall();
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerMessageState());
			// Pushback current line.
			log.setPushback(true);
		}
		else if (isEOM(line)) {
			// Save the attribute to the message.
			log.updateMessage();
			// Update the call with the message.
			log.updateCall();
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerSearchState());
			// Output the line to console.
			//log.dump(line, "     eom");
		}
		else if (isHeader(line)) {
			throw new StateException("Header encountered looking for user data.");
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
