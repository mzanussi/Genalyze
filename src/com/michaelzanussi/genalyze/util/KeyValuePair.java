package com.michaelzanussi.genalyze.util;

import java.util.StringTokenizer;

/**
 * A simple class representing a <code>String</code> key-value pair.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class KeyValuePair {

	private String key;
	private String value;
	
	/**
	 * No-arg constructor.
	 */
	public KeyValuePair() {
		this(null);
	}

	/**
	 * Single-arg constructor. Breaks up passed line into
	 * key-value pair.
	 * 
	 * @param line the line to parse into key and value.
	 */
	public KeyValuePair(String line) {
		key = null;
		value = "";
		
		if (line != null) {
			StringTokenizer st = new StringTokenizer(line);
			
			boolean first = true;
			
	    	while (st.hasMoreTokens()) {
	    		if (first) {
	    			key = st.nextToken().replace('\'', ' ').trim();
	    			first = false;
	    		}
	    		else {
	    			value += st.nextToken().replace('\'', ' ').trim() + " ";
	    		}
	    	}
	    	value = value.trim();
		}
	}

	/**
	 * Returns the key.
	 * 
	 * @return the key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key the key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Returns the value.
	 * 
	 * @return the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * @param value the value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
