package com.michaelzanussi.genalyze.output;

/**
 * This interface defines an object for sending output to, for example
 * the screen or a log file.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (29 January 2007) 
 */
public interface IMonitor {

	/**
	 * Output function.
	 * 
	 * @param data the data to output
	 * @param shr any special handling requirements (null if none)
	 */
	public void output(Object data, String shr);

}
