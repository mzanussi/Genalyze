package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import com.michaelzanussi.genalyze.ui.actions.AboutAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the About menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class AboutMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public AboutMenuItem() {
		super(new AboutAction());
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getAboutMenuItemIcon())));
		setMnemonic(KeyEvent.VK_A);
		setText(GUIPropertyManager.getInstance().getAboutMenuItemText());
	}

}
