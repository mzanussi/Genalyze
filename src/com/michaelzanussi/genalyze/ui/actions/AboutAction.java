package com.michaelzanussi.genalyze.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.michaelzanussi.genalyze.property.GenalyzePropertyManager;
import com.michaelzanussi.genalyze.ui.MainFrame;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the action for the About menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AboutAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor. 
	 */
	public AboutAction() {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		String app = GenalyzePropertyManager.getInstance().getName() + " Test Driver v" + GenalyzePropertyManager.getInstance().getVersion();;
		app += "\n\nDeveloped by Michael Zanussi\nhttp://www.michaelzanussi.com/";
		app += "\n\nSkull box courtesy of Infinise Design\nhttp://www.infinisedesign.com/";
		app += "\n\nSilk Icons courtesy of Mark James\nhttp://www.famfamfam.com/";
		ClassLoader cldr = this.getClass().getClassLoader();
		JOptionPane.showMessageDialog(MainFrame.getInstance(), app, "About", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getAboutDialogIcon())));
	}
}
