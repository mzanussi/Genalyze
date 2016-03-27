package com.michaelzanussi.genalyze.loggers;

import com.michaelzanussi.genalyze.genesys.debug.Debco;

/**
 * This interface defines a log file. It is composed of a single
 * method, <code>process()</code>, for processing the log file specified
 * by the passed filename.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public interface Log {

	/**
	 * Returns the number of log files processed.
	 * 
	 * @return the number of log files processed.
	 */
	public int getNumberOfFiles();

	/**
	 * Returns the number of lines processed in the current file.
	 * 
	 * @return the number of lines processed in the current file.
	 */
	public int getNumberOfLines();

	/**
	 * Should the current line be processed again?
	 * 
	 * @return <code>true</code> if the current line should be
	 * pushed back; otherwise, <code>false</code>.
	 */
	public boolean isPushback();

	/**
	 * Process the log file.
	 * 
	 * @param filename the name of the file to process.
	 */
	public void process(String filename);

	/**
	 * Sets the debug mode.
	 * 
	 * @param debug the debug mode.
	 */
	public void setDebug(boolean debug);

	/**
	 * Returns debug mode.
	 * 
	 * @return debug mode.
	 */
	public boolean isDebug();
	
	/**
	 * Returns the logger debug object, 
	 * used for application logging.
	 * 
	 * @return the logger debug object.
	 */
	public Debco getDebugObj();
	
}
