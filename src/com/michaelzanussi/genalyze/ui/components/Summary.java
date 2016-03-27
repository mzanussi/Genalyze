package com.michaelzanussi.genalyze.ui.components;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import com.michaelzanussi.genalyze.ui.models.SimpleTableModel;

/**
 * The summary window.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class Summary extends JTable {

	private static final long serialVersionUID = 1L;
	private TableModel tablemodel;
	
	/**
	 * The single-arg constructor.
	 * 
	 * @param tablemodel the table mode.
	 */
	public Summary(TableModel tablemodel) {
		this.tablemodel = tablemodel;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(this.tablemodel);
		for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
			getColumnModel().getColumn(i).setMinWidth(getTableModel().getColumnSize(i));
			getColumnModel().getColumn(i).setMaxWidth(getTableModel().getColumnSize(i));
			getColumnModel().getColumn(i).setPreferredWidth(getTableModel().getColumnSize(i));
		}
	}

	/**
	 * Output some data to the summary table.
	 * 
	 * @param object the object containing data to output to the summary table.
	 */
	public void display(Object object) {
		((SimpleTableModel)getModel()).insertRow(object);
	}
	
	/**
	 * Returns the table model being used by the summary table.
	 * 
	 * @return the table model being used by the summary table.
	 */
	private SimpleTableModel getTableModel() {
		return (SimpleTableModel)this.getModel();
	}
}
