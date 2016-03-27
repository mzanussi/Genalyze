package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.StringTokenizer;


/**
 * AttributeNetworkCallID
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (29 December 2008) 
 */
public class AttributeNetworkCallID extends AbstractAttribute {

	private Long value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeNetworkCallID(String value) {
		StringTokenizer st = new StringTokenizer(value);
		String epoch = st.nextToken();
		this.value = stringToLong(epoch);
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
