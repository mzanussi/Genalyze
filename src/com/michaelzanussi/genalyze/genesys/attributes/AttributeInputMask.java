package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.Vector;

import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * AttributeInputMask
 *  
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AttributeInputMask extends AbstractAttribute {

	private String value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeInputMask(String value) {
		this.value = value;
		isListAttribute = true;
		listAttributes = new Vector<KeyValuePair>();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.Attribute#value()
	 */
	public Object value() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.AbstractAttribute#getListAttributes()
	 */
	public Vector<KeyValuePair> getListAttributes() {
		return listAttributes;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.AbstractAttribute#addListAttribute(com.michaelzanussi.genalyze.util.KeyValuePair)
	 */
	public void addListAttribute(KeyValuePair kvp) {
		listAttributes.add(kvp);
	}
	
}
