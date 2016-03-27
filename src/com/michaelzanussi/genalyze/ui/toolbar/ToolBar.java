package com.michaelzanussi.genalyze.ui.toolbar;

import java.awt.Insets;

import javax.swing.JToolBar;

/**
 * This class defines the toolbar.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private OpenToolBarItem open;
	private ProcessToolBarItem process;
	private StopToolBarItem stop;
	
	/**
	 * No-arg constructor. 
	 */
	public ToolBar() {
		// Define the toolbar.
		setMargin(new Insets(5, 5, 5, 5));
		setFloatable(false);
		
		// Create the toolbar item.
		open = new OpenToolBarItem();
		process = new ProcessToolBarItem();
		stop = new StopToolBarItem();
		
		// Add the items to the toolbar.
		add(open);
		addSeparator();
		add(process);
		addSeparator();
		add(stop);
	}
	
	/**
	 * Returns the Open toolbar item.
	 * 
	 * @return the Open toolbar item.
	 */
	public OpenToolBarItem getOpenToolBarItem() {
		return open;
	}

	/**
	 * Returns the Process toolbar item.
	 * 
	 * @return the Process toolbar item.
	 */
	public ProcessToolBarItem getProcessToolBarItem() {
		return process;
	}

	/**
	 * Returns the Stop toolbar item.
	 * 
	 * @return the Stop toolbar item.
	 */
	public StopToolBarItem getStopToolBarItem() {
		return stop;
	}
}
