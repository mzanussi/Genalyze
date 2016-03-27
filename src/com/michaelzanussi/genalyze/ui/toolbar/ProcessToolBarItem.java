package com.michaelzanussi.genalyze.ui.toolbar;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.michaelzanussi.genalyze.ui.actions.ProcessAction;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;

/**
 * This class defines the Process toolbar item.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ProcessToolBarItem extends JButton {

	private static final long serialVersionUID = 1L;
	
	/**
	 * No-arg constructor.
	 */
	public ProcessToolBarItem() {
		super();
		setToolTipText(GUIPropertyManager.getInstance().getProcessToolTip());
		ClassLoader cldr = this.getClass().getClassLoader();
		setIcon(new ImageIcon(cldr.getResource(GUIPropertyManager.getInstance().getProcessMenuItemIcon())));
		setText(GUIPropertyManager.getInstance().getProcessMenuItemText());
		addActionListener(new ProcessAction());
		setFocusable(false);
	}
}
