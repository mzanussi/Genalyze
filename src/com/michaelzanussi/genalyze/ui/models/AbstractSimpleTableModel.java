package com.michaelzanussi.genalyze.ui.models;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * This class provides a skeletal implementation of the <code>SimpleTableModel</code> 
 * interface, to minimize the effort required to implement this interface.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public abstract class AbstractSimpleTableModel extends AbstractTableModel implements SimpleTableModel {

	protected String[] columns;
	protected Vector<Object> data;
	
	private final int SIZE = 128;
	
	/**
	 * No-arg constructor.
	 */
	public AbstractSimpleTableModel() {
		data = new Vector<Object>();
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.ui.models.SimpleTableModel#insertRow(java.lang.Object)
	 */
	public synchronized void insertRow(Object object) {
    	if (data.size() == SIZE) {
    		data.remove(data.size()-1);
    	}
    	data.insertElementAt(object, 0);
	    fireTableRowsInserted(0, 0);
	}

    /* (non-Javadoc)
     * @see com.michaelzanussi.genalyze.ui.models.SimpleTableModel#empty()
     */
    public synchronized void empty() {
	    if (data.size() > 0) {
		    int size = data.size();
			data.removeAllElements();
		    fireTableRowsDeleted(0, size - 1);
	    }
	}

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
		return columns.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return data.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public abstract Object getValueAt(int rowIndex, int columnIndex);

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
        return columns[column];
    }

}
