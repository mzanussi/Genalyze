package com.michaelzanussi.genalyze.ui.toolbar;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.michaelzanussi.genalyze.ui.actions.OpenAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Open toolbar item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class OpenToolBarItem extends JButton {

	private static final long serialVersionUID = 1L;
	
	/**
	 * No-arg constructor.
	 */
	public OpenToolBarItem() {
		super();
		setToolTipText(GUIPropertyManager.getInstance().getOpenToolTip());
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getOpenMenuItemIcon())));
		setText(GUIPropertyManager.getInstance().getOpenMenuItemText());
		addActionListener(new OpenAction());
		setFocusable(false);
	}
}
