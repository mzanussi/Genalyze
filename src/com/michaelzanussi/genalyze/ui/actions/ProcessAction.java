package com.michaelzanussi.genalyze.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.michaelzanussi.genalyze.ui.MainFrame;

/**
 * This class defines the action for the Process menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ProcessAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor. 
	 */
	public ProcessAction() {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		MainFrame.getMenu().getToolsMenu().getProcessMenuItem().setEnabled(false);
		MainFrame.getMenu().getFileMenu().getOpenMenuItem().setEnabled(false);
		MainFrame.getMenu().getToolsMenu().getOptionsMenu().setEnabled(false);
		MainFrame.getMenu().getToolsMenu().getStopMenuItem().setEnabled(true);
		MainFrame.getThread().start();
		MainFrame.getToolBar().getOpenToolBarItem().setEnabled(false);
		MainFrame.getToolBar().getProcessToolBarItem().setEnabled(false);
		MainFrame.getToolBar().getStopToolBarItem().setEnabled(true);
	}
}
