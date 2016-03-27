package com.michaelzanussi.genalyze.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;

import com.michaelzanussi.genalyze.ui.MainFrame;

/**
 * This class defines the action for the Debug output option.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class DebugOutputAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public DebugOutputAction() {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		JCheckBoxMenuItem debout = (JCheckBoxMenuItem)event.getSource();
		MainFrame.setDebug(debout.isSelected());
	}
}
