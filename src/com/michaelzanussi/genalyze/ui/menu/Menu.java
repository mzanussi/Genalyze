package com.michaelzanussi.genalyze.ui.menu;

import javax.swing.JMenuBar;

/**
 * This class defines the menu bar.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class Menu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private FileMenu file;
	private ToolsMenu tools;
	private HelpMenu help;

	/**
	 * No-arg constructor.
	 */
	public Menu() {
		
		// Create the menus.
		file = new FileMenu();
		tools = new ToolsMenu();
		help = new HelpMenu();
		
		// Add menus to the menu bar.
		add(file);
		add(tools);
		add(help);
		
	}
	
	/**
	 * Returns the File menu.
	 * 
	 * @return the File menu
	 */
	public FileMenu getFileMenu() {
		return file;
	}
	
	/**
	 * Returns the Tools menu.
	 * 
	 * @return the Tools menu
	 */
	public ToolsMenu getToolsMenu() {
		return tools;
	}
	
	/**
	 * Returns the Help menu.
	 * 
	 * @return the Help menu
	 */
	public HelpMenu getHelpMenu() {
		return help;
	}
}
