package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Tools menu.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ToolsMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	private OptionsSubmenu optionsMenu;
	private ProcessMenuItem processMenuItem;
	private StopMenuItem stopMenuItem;

	/**
	 * No-arg constructor.
	 */
	public ToolsMenu() {
		
		setText(GUIPropertyManager.getInstance().getToolsMenuText());
		setMnemonic(KeyEvent.VK_T);

		// Create menu items.
		optionsMenu = new OptionsSubmenu(); 
		processMenuItem = new ProcessMenuItem();
		stopMenuItem = new StopMenuItem();

		// Add the menu items.
		add(optionsMenu);
		addSeparator();
		add(processMenuItem);
		add(stopMenuItem);

	}
	
	/**
	 * Returns the Options submenu.
	 * 
	 * @return the Options submenu 
	 */
	public OptionsSubmenu getOptionsMenu() {
		return optionsMenu;
	}
	
	/**
	 * Returns the Process menu item.
	 * 
	 * @return the Process menu item
	 */
	public ProcessMenuItem getProcessMenuItem() {
		return processMenuItem;
	}
	
	/**
	 * Returns the Stop menu item.
	 * 
	 * @return the Stop menu item
	 */
	public StopMenuItem getStopMenuItem() {
		return stopMenuItem;
	}
	
}
