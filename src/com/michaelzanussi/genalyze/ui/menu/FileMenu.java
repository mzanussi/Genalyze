package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the File menu.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class FileMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	private OpenMenuItem openMenuItem;
	private ExitMenuItem exitMenuItem;
	
	/**
	 * No-arg constructor.
	 */
	public FileMenu() {

		setText(GUIPropertyManager.getInstance().getFileMenuText());
		setMnemonic(KeyEvent.VK_F);

		// Create menu items.
		openMenuItem = new OpenMenuItem();
		exitMenuItem = new ExitMenuItem();
		
		// Add the menu items.
		add(openMenuItem);
		addSeparator();
		add(exitMenuItem);
	}
	
	/**
	 * Returns the Open menu item.
	 * 
	 * @return the Open menu item.
	 */
	public OpenMenuItem getOpenMenuItem() {
		return openMenuItem;
	}
	
	/**
	 * Returns the Exit menu item.
	 * 
	 * @return the Exit menu item.
	 */
	public ExitMenuItem getExitMenuItem() {
		return exitMenuItem;
	}
}
