package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Help menu.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class HelpMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	private AboutMenuItem aboutMenuItem;
	
	/**
	 * No-arg constructor
	 */
	public HelpMenu() {

		setText(GUIPropertyManager.getInstance().getHelpMenuText());
		setMnemonic(KeyEvent.VK_H);

		// Create menu items.
		aboutMenuItem = new AboutMenuItem();
		
		// Add the menu items.
		add(aboutMenuItem);
	}
	
	/**
	 * Returns the About menu item.
	 * 
	 * @return the About menu item.
	 */
	public AboutMenuItem getAboutMenuItem() {
		return aboutMenuItem;
	}
}
