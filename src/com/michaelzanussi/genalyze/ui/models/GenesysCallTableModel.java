package com.michaelzanussi.genalyze.ui.models;

import com.michaelzanussi.genalyze.genesys.call.Call;

/**
 * This concrete class defines the summary table model for Genesys call data. 
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class GenesysCallTableModel extends AbstractSimpleTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * No-arg constructor.
	 */
	public GenesysCallTableModel() {
		super();
		columns = new String[] {"ConnID", "DNIS", "ANI"};
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.ui.models.AbstractSimpleTableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Call call = (Call)data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return call.getConnID();
		case 1:
			return call.getDNIS();
		case 2:
			return call.getANI();
		default:
			return new Object();
		}
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.ui.models.SimpleTableModel#getColumnSize(int)
	 */
	public int getColumnSize(int index) {
		switch (index) {
		case 0:		// ConnID
			return 125;
		case 1:		// DNIS
			return 100;
		case 2:		// ANI
			return 100;
		default:
			return 100;
		}
	}

}
