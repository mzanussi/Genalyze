package com.michaelzanussi.genalyze.genesys.attributes;


/**
 * AttributeHomeLocation
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AttributeHomeLocation extends AbstractAttribute {

	private String value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeHomeLocation(String value) {
		this.value = value;
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

}
