package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import com.michaelzanussi.genalyze.ui.actions.StopAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Cancel menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class StopMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public StopMenuItem() {
		super(new StopAction());
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getStopMenuItemIcon())));
		setMnemonic(KeyEvent.VK_C);
		setText(GUIPropertyManager.getInstance().getStopMenuItemText());
	}

}
