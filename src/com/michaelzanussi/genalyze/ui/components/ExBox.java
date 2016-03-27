package com.michaelzanussi.genalyze.ui.components;

import javax.swing.JOptionPane;

import com.michaelzanussi.genalyze.property.GenalyzePropertyManager;
import com.michaelzanussi.genalyze.ui.MainFrame;

/**
 * Simple exception dialog box. Provides feedback to users of application
 * executable outside of IDE in the event of an exception.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (2 January 2009) 
 */
public class ExBox {

	public static void disp(String msg) {
		if (msg == null) {
			msg = "{nil}";
		}
		String title = GenalyzePropertyManager.getInstance().getName() + " Test Driver v" + GenalyzePropertyManager.getInstance().getVersion();
		JOptionPane.showMessageDialog(MainFrame.getInstance(), msg, title, JOptionPane.ERROR_MESSAGE, null);
	}

}
