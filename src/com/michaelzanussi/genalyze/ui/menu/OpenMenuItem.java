package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import com.michaelzanussi.genalyze.ui.actions.OpenAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Open menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class OpenMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public OpenMenuItem() {
		super(new OpenAction());
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getOpenMenuItemIcon())));
		setMnemonic(KeyEvent.VK_O);
		setText(GUIPropertyManager.getInstance().getOpenMenuItemText());
	}

}
