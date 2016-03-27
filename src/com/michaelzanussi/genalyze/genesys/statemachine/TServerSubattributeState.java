package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * T-Server subattribute state.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class TServerSubattributeState extends AbstractTServerState {

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerSubattributeState(Genesys log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isSubattribute(line)) {
			// Output the line to console.
			//log.dump(line, "     sub");

			// Generate a key-value pair containing the subattribute and value.
			//KeyValuePair kvp = new KeyValuePair(line);
			
			// TODO: Handle subattributes, such as AttributeExtensions.
			// (Note that Userdata subattributes are handled separately).
			// Currently, this information is ignored, but could very well
			// be stored with the current message.
			
		}
		else if (isAttribute(line)) {
			// Save the attribute to the message.
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
			throw new StateException("Header encountered looking for subattribute.");
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
