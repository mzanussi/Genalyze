package com.michaelzanussi.genalyze.ui.components;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.michaelzanussi.genalyze.ui.renderers.LogDumpCellRenderer;

/**
 * The log dump window.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class LogDump extends JList {

	private static final long serialVersionUID = 1L;
	private DefaultListModel logdump = new DefaultListModel();
	
	/**
	 * No-arg constructor.
	 */
	public LogDump() {
		setModel(logdump);
		setCellRenderer(new LogDumpCellRenderer());
	}
	
	/**
	 * Display a line from the log to the log dump.
	 * 
	 * @param line the line to display in the log dump.
	 */
	public void display(String line) {
		if (logdump.size() == 17) {
			logdump.remove(0);
		}
		logdump.addElement(line);
	}

}
