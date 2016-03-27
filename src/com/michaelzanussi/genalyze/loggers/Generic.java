package com.michaelzanussi.genalyze.loggers;

/**
 * This interface defines a generic log file.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (19 January 2007) 
 */
public interface Generic {

	/**
	 * Sends specified data to the log dump, if one has been defined.
	 * 
	 * @param line the data to dump
	 * @param tag a tag
	 */
	public void dump(String line, String tag);

	/** 
	 * Sets the pushback status on the current line.
	 * 
	 * @param pushback the pushback status on the current line.
	 */
	public void setPushback(boolean pushback);

}
