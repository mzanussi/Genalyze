package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.StringTokenizer;


/**
 * AttributeCallInfoType
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (7 January 2009) 
 */
public class AttributeCallInfoType extends AbstractAttribute {

	private Long value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeCallInfoType(String value) {
		StringTokenizer st = new StringTokenizer(value);
		String type = st.nextToken();
		this.value = stringToLong(type);
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
