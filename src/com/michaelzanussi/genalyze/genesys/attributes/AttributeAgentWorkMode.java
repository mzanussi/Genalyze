package com.michaelzanussi.genalyze.genesys.attributes;

import java.util.StringTokenizer;


/**
 * AttributeAgentWorkMode
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AttributeAgentWorkMode extends AbstractAttribute {

	private Integer value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeAgentWorkMode(String value) {
		StringTokenizer st = new StringTokenizer(value);
		String mode = st.nextToken();
		this.value = stringToInteger(mode);
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
