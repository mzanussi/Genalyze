package com.michaelzanussi.genalyze.genesys.attributes;


/**
 * AttributeThisDNRole
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AttributeThisDNRole extends AbstractAttribute {

	private Integer value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeThisDNRole(String value) {
		this.value = stringToInteger(value);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return value.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.genesys.attributes.Attribute#value()
	 */
	public Object value() {
		return value;
	}

}
