package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import com.michaelzanussi.genalyze.property.GenalyzePropertyManager;
import com.michaelzanussi.genalyze.ui.actions.DebugOutputAction;
import com.michaelzanussi.genalyze.ui.actions.UserDataAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Options submenu.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class OptionsSubmenu extends JMenu {

	private static final long serialVersionUID = 1L;
	private static JCheckBoxMenuItem debout;
	private static JCheckBoxMenuItem userdata;
	
	/**
	 * No-arg constructor.
	 */
	public OptionsSubmenu() {
		
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getOptionsMenuIcon())));
		setMnemonic(KeyEvent.VK_O);
		setText(GUIPropertyManager.getInstance().getOptionsMenuText());

		debout = new JCheckBoxMenuItem(new DebugOutputAction());
		debout.setText(GUIPropertyManager.getInstance().getDebugOutputOptionText());
		debout.setSelected(GenalyzePropertyManager.getInstance().isDebug());
		
		userdata = new JCheckBoxMenuItem(new UserDataAction());
		userdata.setText(GUIPropertyManager.getInstance().getUserDataOptionText());
		userdata.setSelected(GenalyzePropertyManager.getInstance().isStoreUserData());
		
		add(debout);
		add(userdata);
	}
	
	/**
	 * Returns the debug output option.
	 * 
	 * @return the debug output option
	 */
	public JCheckBoxMenuItem getDebugOutput() {
		return debout;
	}
	
	/**
	 * Returns the user data option.
	 * 
	 * @return the user data option
	 */
	public JCheckBoxMenuItem getUserData() {
		return userdata;
	}
}
