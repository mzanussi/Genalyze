package com.michaelzanussi.genalyze.output;

import java.awt.EventQueue;

import com.michaelzanussi.genalyze.genesys.call.Call;
import com.michaelzanussi.genalyze.ui.MainFrame;
import com.michaelzanussi.genalyze.ui.dispatchers.ConsoleDispatcher;
import com.michaelzanussi.genalyze.ui.dispatchers.LogDumpDispatcher;

/**
 * A concrete implementation of <code>IMonitor</code> for outputing data.
 * TODO: Add file output handling.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (29 January 2007) 
 */
public class Monitor implements IMonitor {

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.IMonitor#output(java.lang.Object, java.lang.String)
	 */
	public void output(Object data, String shr) {
		if (data instanceof String) {
			String outp = (String)data;
			/*if (MainFrame.isDebug()) {
				System.out.println(outp);
			}*/
			
			if ("console".equalsIgnoreCase(shr)) {
				EventQueue.invokeLater(new ConsoleDispatcher(outp));
			}
			else {
				EventQueue.invokeLater(new LogDumpDispatcher(outp));
			}
		}
		else if (data instanceof Call) {
			Call call = (Call)data;
			MainFrame.getSummary().display(call);
		}
	}

}
