package com.michaelzanussi.genalyze.genesys.statemachine;

import java.io.IOException;

import com.michaelzanussi.genalyze.genesys.messages.Message;
import com.michaelzanussi.genalyze.loggers.Genesys;

/**
 * T-Server message state.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class TServerMessageState extends AbstractTServerState {

	/**
	 * Single-arg constructor.
	 * 
	 * @param log the log.
	 */
	public TServerMessageState(Genesys log) {
		this.log = log;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.statemachine.State#exec(java.lang.String)
	 */
	public void exec(String line) throws StateException {
		if (isTServerEvent(line)) {
			try {
				// An event has been encountered.
				//log.dump(line, "   EVENT");
				// Get the event name.
	   			String name = getEventName(line);
	   			// Create the message.
	   			createMessage(name);
			}
			catch (IOException io) {
				throw new StateException(io.getMessage());
			}
    	}
		else if (isTServerRequest(line)) {
			try {
				// A request has been encountered.
				//log.dump(line, " REQUEST");
				// Get the message name.
	   			String name = getRequestName(line);
	   			// Create the message.
	   			createMessage(name);
			}
			catch (IOException io) {
				throw new StateException(io.getMessage());
			}
		}
		else if (isAttribute(line)) {
			throw new StateException("Attribute encountered looking for message.");
		}
		else if (isSubattribute(line)) {
			throw new StateException("Subattribute encountered looking for message.");
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

	/**
	 * Create the message object.
	 * 
	 * @param name the message to create.
	 */
	private void createMessage(String name) {
		try {
			// Use factory object to create the message object.
			Message msg = log.factory().createMessage(name);
			// Store the message object with the T-server object,
			// increment message count.
			log.setMessage(msg);
			log.incrementNumberOfMessages();
			// CHANGE STATE
			log.getStateMachine().setState(log.getStateMachine().getTServerAttributeState());
		}
		catch (Exception ex) {
			log.showException(this, "exec", ex.toString() + "\n(new attribute found during createMessage)");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * Returns the event name.
	 * 
	 * @param line the line to process.
	 * @return the event name.
	 * @throws IOException
	 */
	private String getEventName(String line) throws IOException {
		String type = null;
		
		String string = ": message";
		int beg = line.indexOf(string);
		
		if (beg == -1) {
			throw new IOException("No message name found for this event!");
		}
		
		type = line.substring(beg + string.length() + 1);
		
		return type;
	}
	
	/**
	 * Returns the request name.
	 * 
	 * @param line the line to process.
	 * @return the request name.
	 * @throws IOException
	 */
	private String getRequestName(String line) throws IOException {
		return line.substring("message ".length());
	}
	
}
