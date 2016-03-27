package com.michaelzanussi.genalyze.ui.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import com.michaelzanussi.genalyze.ui.MainFrame;

/**
 * This class defines the action for the Open menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class OpenAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public OpenAction() {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		JFileChooser file = new JFileChooser();
		File dir = MainFrame.getLogDirectory();
		file.setCurrentDirectory(dir);
		file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int choice = file.showDialog(MainFrame.getInstance(), "Select Folder");
		if (choice != JFileChooser.CANCEL_OPTION) {
			MainFrame.setLogDirectory(file.getSelectedFile());
		}
	}
}
