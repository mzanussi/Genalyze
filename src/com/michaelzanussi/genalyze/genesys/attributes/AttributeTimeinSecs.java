package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.StringTokenizer;


/**
 * AttributeTimeinSecs
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AttributeTimeinSecs extends AbstractAttribute {

	private Long value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeTimeinSecs(String value) {
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
