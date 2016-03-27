package com.michaelzanussi.genalyze.genesys.attributes;


/**
 * AttributeServerStartTime
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (7 January 2009) 
 */
public class AttributeServerStartTime extends AbstractAttribute {

	private Long value;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param value attribute value.
	 */
	public AttributeServerStartTime(String value) {
		// TODO: need to convert this value
		//StringTokenizer st = new StringTokenizer(value);
		//String epoch = st.nextToken();
		//this.value = stringToLong(epoch);
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
