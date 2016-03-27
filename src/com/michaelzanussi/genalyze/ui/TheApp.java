package com.michaelzanussi.genalyze.ui;

/**
 * The Genalzye test driver application.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class TheApp {

	// Useful VM args:
	//		-Xms256m -Xmx512m
	
	/**
	 * Entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame frame = MainFrame.getInstance();
		frame.setVisible(true);
	}

}
