package com.michaelzanussi.genalyze.ui.components;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.michaelzanussi.genalyze.ui.renderers.ConsoleCellRenderer;

/**
 * The console window.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class Console extends JList {

	private static final long serialVersionUID = 1L;
	private DefaultListModel console = new DefaultListModel();
	
	/**
	 * No-arg constructor.
	 */
	public Console() {
		setModel(console);
		setCellRenderer(new ConsoleCellRenderer());
	}
	
	/**
	 * Display a line of text in the console.
	 * 
	 * @param line the line of text to display in the console.
	 */
	public void display(String line) {
		if (console.size() == 50) {
			console.remove(0);
		}
		console.addElement(line);
	}

}
