package com.michaelzanussi.genalyze.genesys.messages;

import java.util.Vector;

import com.michaelzanussi.genalyze.genesys.attributes.Attribute;

/**
 * This interface defines a T-library message (either an event or a request).
 * The <code>Message</code> interface is likely to be modified for each
 * individual Genesys installation, depending on the data needs of each site.
 * See examples provided with code.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public interface Message {
	
	/**
	 * Returns the name of the T-library message. See also getName().
	 * 
	 * @return the name of the T-library message.
	 */
	public String toString();
	
	/**
	 * Returns the name of the T-library message. See also toString().
	 * 
	 * @return the name of the T-library message.
	 */
	public String getName();
	
	/**
	 * Returns a <code>Vector</code> containing the message attributes.
	 * 
	 * @return a <code>Vector</code> containing the message attributes.
	 */
	public Vector<Attribute> getAttributes();
	
	/**
	 * Adds an attribute key-value pair to the message attributes.
	 * 
	 * @param kvp the key-value pair to add to the message attributes.
	 */
	public void addAttribute(Attribute attr);

	/**
	 * Sets the connection ID for this message. ConnID can also
	 * be found as an attribute key-value pair.
	 * 
	 * @param connID the connection ID to set.
	 */
	public void setConnID(String connID);
	
	/**
	 * Returns the connection ID for this message.
	 * 
	 * @return the connection ID for this message.
	 */
	public String getConnID();

	/**
	 * Sets the DNIS for this message. DNIS can also be found as
	 * an attribute key-value pair.
	 * 
	 * @param dnis the DNIS to set.
	 */
	public void setDNIS(String dnis);
	
	/**
	 * Returns the DNIS for this message.
	 * 
	 * @return the DNIS for this message.
	 */
	public String getDNIS();

	/**
	 * Sets the ANI for this message. ANI can also be found as
	 * an attribute key-value pair.
	 * 
	 * @param ani the ANI to set.
	 */
	public void setANI(String ani);
	
	/**
	 * Returns the ANI for this message.
	 * 
	 * @return the ANI for this message.
	 */
	public String getANI();

	// ***********************************************************
	// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************
	
	/**
	 * Non-standard Genesys customization: returns the EWT. 
	 * 
	 * @return the userdata item
	 */
	public int getEWT();
	
	/**
	 * Non-standard Genesys customization: sets the EWT.
	 * 
	 * @param ewt set the userdata item
	 */
	public void setEWT(String ewt);

	// ***********************************************************
	// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA] 
	// ***********************************************************
	
}
