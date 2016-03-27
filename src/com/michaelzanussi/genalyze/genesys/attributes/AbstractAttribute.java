package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.Vector;

import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * This class provides a skeletal implementation of the <code>Attribute</code> 
 * interface, to minimize the effort required to implement this interface.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public abstract class AbstractAttribute implements Attribute {

	/**
	 * Is this attribute a container for list attributes?
	 */
	protected boolean isListAttribute = false;
	
	/**
	 * The vector containing this attribute's list attributes.
	 * TODO: Vector might not be a suitable data structure since
	 * searches will need to be done on this data.
	 */
	protected Vector<KeyValuePair> listAttributes = null;
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.Attribute#isListAttribute()
	 */
	public boolean isListAttribute() {
		return isListAttribute;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.Attribute#getListAttributes()
	 */
	public Vector<KeyValuePair> getListAttributes() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.Attribute#addListAttribute(com.michaelzanussi.genalyze.util.KeyValuePair)
	 */
	public void addListAttribute(KeyValuePair kvp) {
		// Defer to concrete classes for implemention.
	}
	
	/**
	 * Converts the String argument to a Long.
	 * 
	 * @param in the input value as a String.
	 * @return the output value as a Long.
	 */
	protected Long stringToLong(String in) {
		Long out = null;
		try {
			out = new Long(in);
		}
		catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return out;
	}

	/**
	 * Converts the String argument to an Integer.
	 * 
	 * @param in the input value as a String.
	 * @return the output value as an Integer.
	 */
	protected Integer stringToInteger(String in) {
		Integer out = null;
		try {
			out = new Integer(in);
		}
		catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return out;
	}
	
}
