package com.michaelzanussi.genalyze.ui.dispatchers;

import com.michaelzanussi.genalyze.ui.MainFrame;

/**
 * AWT thread event dispatcher provided for special output.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ConsoleDispatcher implements Runnable {

	private final String string;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param string the string to output to console.
	 */
	public ConsoleDispatcher(String string) {
		this.string = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		MainFrame.getConsole().display(string);
	}

}
