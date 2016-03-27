package com.michaelzanussi.genalyze.ui.menu;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import com.michaelzanussi.genalyze.ui.actions.ExitAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Exit menu item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ExitMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public ExitMenuItem() {
		super(new ExitAction());
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getExitMenuItemIcon())));
		setMnemonic(KeyEvent.VK_X);
		setText(GUIPropertyManager.getInstance().getExitMenuItemText());
	}

}
