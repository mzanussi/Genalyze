package com.michaelzanussi.genalyze.ui.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Cell renderer for the console window.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ConsoleCellRenderer extends JLabel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public ConsoleCellRenderer() {
		setOpaque(true);
		Font font = new Font("Trebuchet MS", Font.PLAIN, 11);
		setFont(font);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
		setText(value.toString());
		setBackground(isSelected ? Color.BLUE : Color.WHITE);
        setForeground(isSelected ? Color.WHITE : Color.BLUE);
		return this;
	}
}
