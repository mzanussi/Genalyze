package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.Vector;

import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * This interface defines an event/request attribute.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public interface Attribute {

	/**
	 * Returns the string representation of the attribute's value.
	 * 
	 * @return the string representation of the attribute's value.
	 */
	public String toString();
	
	/**
	 * Returns the attribute's value.
	 * 
	 * @return the attribute's value.
	 */
	public Object value();
	
	/**
	 * Does this attribute have list attributes?
	 * 
	 * @return <code>true</code> if this attribute has list attributes;
	 * otherwise, <code>false</code>.
	 */
	public boolean isListAttribute();
	
	/**
	 * Returns the list attributes for this attribute.
	 * 
	 * @return the list attributes for this attribute.
	 */
	public Vector<KeyValuePair> getListAttributes();
	
	/**
	 * Adds a list attribute to this attribute.
	 * 
	 * @param kvp the key-value pair to add.
	 */
	public void addListAttribute(KeyValuePair kvp);

}
