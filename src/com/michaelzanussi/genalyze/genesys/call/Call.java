package com.michaelzanussi.genalyze.genesys.call;

import java.util.Vector;

import com.michaelzanussi.genalyze.genesys.messages.Message;

/**
 * This class defines a call in Genesys. A call is made up of messages, 
 * which in turn contain attributes, which themselves might have sub-attributes (these
 * are the key-value pairs of a list attribute such as AttributeUserData). In 
 * addition, a Call will have some common data which is shared amongst all message types,
 * such as a ConnID, and possibly might define other non-standard Genesys data such as 
 * userdata items.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class Call {

	private String connID;
	private Vector<Message> messages;
	private String timeInSecs;
	private String DNIS;
	private String ANI;

	// ***********************************************************
	// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************

	private int EWT = 0;
	
	// ***********************************************************
	// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************
	
	/**
	 * No-arg constructor.
	 */
	public Call() {
		connID = null;
		messages = new Vector<Message>();
		timeInSecs = null;
		DNIS = null;
		ANI = null;
	}

	/**
	 * Set time in seconds that this call originated.
	 * 
	 * @param timeInSecs the time in seconds.
	 */
	public void setTimeInSecs(String timeInSecs) {
		if (this.timeInSecs == null) {
			this.timeInSecs = timeInSecs;
		}
	}
	
	/**
	 * Returns the time in seconds when the call originated.
	 * 
	 * @return the time in seconds when the call originated.
	 */
	public String getTimeInSecs() {
		return timeInSecs;
	}
	
	/**
	 * Returns the connection ID.
	 * 
	 * @return the connection ID.
	 */
	public String getConnID() {
		return connID;
	}

	/**
	 * Sets the connection ID.
	 * 
	 * @param connID the connection ID.
	 */
	public void setConnID(String connID) {
		if (this.connID == null) {
			this.connID = connID;
		}
	}

	/**
	 * Returns the DNIS.
	 * 
	 * @return the DNIS.
	 */
	public String getDNIS() {
		return DNIS;
	}

	/**
	 * Sets the DNIS.
	 * 
	 * @param DNIS the DNIS.
	 */
	public void setDNIS(String DNIS) {
		if (this.DNIS == null) {
			this.DNIS = DNIS;
		}
	}

	/**
	 * Returns the ANI.
	 * 
	 * @return the ANI.
	 */
	public String getANI() {
		return ANI;
	}

	/**
	 * Sets the ANI.
	 * 
	 * @param ANI the ANI.
	 */
	public void setANI(String ANI) {
		if (this.ANI == null) {
			this.ANI = ANI;
		}
	}

	/**
	 * Returns this call's associated messages.
	 * 
	 * @return this call's associated messages.
	 */
	public Vector<Message> getMessages() {
		return messages;
	}

	/**
	 * Add a message to this call's message list.
	 * 
	 * @param message the message to add to the call's message list.
	 */
	public void addMessage(Message message) {
		if (message != null) {
			messages.add(message);
		}
	}
	
	// ***********************************************************
	// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************

	/**
	 * Returns the estimated wait time.
	 * 
	 * @return the estimated wait time.
	 */
	public int getEWT() {
		return EWT;
	}
	
	/**
	 * Sets the estimated wait time.
	 * 
	 * @param EWT the estimated wait time.
	 */
	public void setEWT(int EWT) {
		this.EWT = EWT;
	}

	// ***********************************************************
	// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************
	
}
