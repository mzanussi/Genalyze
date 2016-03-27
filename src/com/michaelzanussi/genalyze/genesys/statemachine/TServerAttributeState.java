package com.michaelzanussi.genalyze.genesys.statemachine;

import com.michaelzanussi.genalyze.genesys.attributes.Attribute;
import com.michaelzanussi.genalyze.genesys.attributes.AttributeANI;
import com.michaelzanussi.genalyze.genesys.attributes.AttributeConnID;
import com.michaelzanussi.genalyze.genesys.attributes.AttributeDNIS;
import com.michaelzanussi.genalyze.genesys.attributes.AttributeUserData;
import com.michaelzanussi.genalyze.loggers.Genesys;
import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * T-Server attribute state.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class TServerAttributeState extends AbstractTServerState {

	private Attribute attribute;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerAttributeState(Genesys log) {
		this.log = log;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isAttribute(line)) {
			// Generate a key-value pair containing the attribute and value.
			KeyValuePair kvp = new KeyValuePair(line);

			try {
				// Create the attribute using the key-value pair.
				attribute = log.factory().createAttribute(kvp);

				// Check if list attribute.
				if (attribute.isListAttribute()) {
					if (attribute instanceof AttributeUserData) {
						// Store the current attribute to the log object.
						// It will need to be stored with the message itself
						// after processing the list attributes.
						log.setAttribute(attribute);
						log.getStateMachine().setState(log.getStateMachine().getTServerUserDataState());
					}
					else {
						// For other list attributes, just log the attribute
						// and continue. AttributeState will pickup the sub attributes
						// and do nothing with them. See type UserDataState for an
						// example of how to handle list attributes.
						//log.getMessage().addAttribute(attribute);
						log.setAttribute(attribute);
						log.getStateMachine().setState(log.getStateMachine().getTServerSubattributeState());
					}
				}
				else {
					// This is a standard attribute, so add to message's attribute vector.
					log.getMessage().addAttribute(attribute);
					// MBZ: For convenience, store off the ConnID with the 
					// Message object, or any other attribute for that matter.
					// Such info will always be available as an attribute
					// key-value pair stored with the message.
					if (attribute instanceof AttributeConnID) {
						log.getMessage().setConnID(attribute.toString());
					}
					else if (attribute instanceof AttributeDNIS) {
						log.getMessage().setDNIS(attribute.toString());
					}
					else if (attribute instanceof AttributeANI) {
						log.getMessage().setANI(attribute.toString());
					}
				}
				// Output the line to console.
				//log.dump(line, "    attr");
			}
			catch (Exception ex) {
				log.showException(this, "exec", ex.toString() + "\n(new attribute found during createAttribute)");
				ex.printStackTrace();
				System.exit(0);
			}

		}
		else if (isSubattribute(line)) {
			throw new StateException("Encountered a subattribute looking for an attribute.");
		}
		else if (isMessage(line)) {
			// This new message ends the old message. Update call.
			log.updateCall();
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerMessageState());
			// Pushback current line.
			log.setPushback(true);
		}
		else if (isEOM(line)) {
			// Update the call with the message.
			log.updateCall();
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerSearchState());
			// Output the line to console.
			//log.dump(line, "     eom");
		}
		else if (isHeader(line)) {
			throw new StateException("Encountered header looking for an attribute.");
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
