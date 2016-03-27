package com.michaelzanussi.genalyze.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * This class defines the action for the Exit menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ExitAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor. 
	 */
	public ExitAction() {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		System.exit(0);
	}
}
