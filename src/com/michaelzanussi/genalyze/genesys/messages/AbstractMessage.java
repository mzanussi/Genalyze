package com.michaelzanussi.genalyze.genesys.messages;

import java.util.Vector;

import com.michaelzanussi.genalyze.genesys.attributes.Attribute;

/**
 * This class provides a skeletal implementation of the <code>Message</code> 
 * interface, to minimize the effort required to implement this interface.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public abstract class AbstractMessage implements Message {

	protected String name;						// the message name
	protected Vector<Attribute> attributes;		// this message's attributes
	
	private String connID;			// this message's connection ID.
	private String dnis;			// this message's DNIS.
	private String ani;				// this message's ANI.

	// ***********************************************************
	// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************
	
	private int ewt = 0;
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#getEWT()
	 */
	public int getEWT() {
		return ewt;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#setEWT(java.lang.String)
	 */
	public void setEWT(String ewt) {
		try {
			this.ewt = Integer.parseInt(ewt);
		}
		catch (NumberFormatException nfe) {
			this.ewt = 0;
		}
	}
	
	// ***********************************************************
	// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA] 
	// ***********************************************************
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param name the message name.
	 */
	protected AbstractMessage(String name) {
		this.name = name;
		attributes = new Vector<Attribute>();
		connID = null;
		dnis = null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#getName()
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#getAttributes()
	 */
	public Vector<Attribute> getAttributes() {
		return attributes;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#addAttribute(com.michaelzanussi.genalyze.genesys.attributes.Attribute)
	 */
	public void addAttribute(Attribute attr) {
		if (attr == null) {
			throw new NullPointerException("No attribute specified.");
		}
		attributes.add(attr);
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#getConnID()
	 */
	public String getConnID() {
		return connID;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#setConnID(java.lang.String)
	 */
	public void setConnID(String connID) {
		this.connID = connID;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#getDNIS()
	 */
	public String getDNIS() {
		return dnis;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#setDNIS(java.lang.String)
	 */
	public void setDNIS(String dnis) {
		this.dnis = dnis;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#getANI()
	 */
	public String getANI() {
		return ani;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.messages.Message#setANI(java.lang.String)
	 */
	public void setANI(String ani) {
		this.ani = ani;
	}
	
}
