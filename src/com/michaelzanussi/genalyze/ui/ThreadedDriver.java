package com.michaelzanussi.genalyze.ui;

import java.awt.EventQueue;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Arrays;

import com.michaelzanussi.genalyze.loggers.Log;
import com.michaelzanussi.genalyze.ui.dispatchers.ConsoleDispatcher;

/**
 * Test driver for the Genalyze GUI.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class ThreadedDriver extends Thread {
	
	private boolean stopThread;			// stop the thread?
	private Log log;					// the logger
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param log the logger to use for processing.
	 */
	public ThreadedDriver(Log log) {
		stopThread = false;
		this.log = log; 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		
		// Retrieve the log directory name and the file list.
		File dir = MainFrame.getLogDirectory();
		String[] files = dir.list();
		
		// Set the logger debug mode.
		log.setDebug(MainFrame.isDebug());
		
		// Log the status.
		setName("genalyze");
		log.getDebugObj().getAppLogger().info("Genalyze started!");
		
		// Logs need to be in date-time order to process
		// with any meaningful results.
		Arrays.sort(files);

		if (files == null || files.length == 0) {
			// Nothing to do.
			log.getDebugObj().getAppLogger().info("No logs to process.");
			MainFrame.setStatus("No logs to process.");
		}
		else {
			// Process each log file.
			for (int i = 0; i < files.length; i++) {

				if (MainFrame.isDebug()) {
					mem_status(i);
				}
				
				String filename = files[i];
				
				String msg = "Processing (" + (i+1) + "/" + files.length + ") " + filename;
				MainFrame.setStatus(msg);
				EventQueue.invokeLater(new ConsoleDispatcher(msg));
				log.getDebugObj().getAppLogger().info(msg);
				
				// Do the processing.
				log.process(dir + "/" + filename);
				
				if (stopThread) {
					break;
				}
			}
			log.getDebugObj().getAppLogger().info("Done!");
			MainFrame.setStatus("Done!");
		}

		// Re-enable Process button on main form.
		MainFrame.getMenu().getFileMenu().getOpenMenuItem().setEnabled(true);
		MainFrame.getMenu().getToolsMenu().getOptionsMenu().setEnabled(true);
		MainFrame.getMenu().getToolsMenu().getProcessMenuItem().setEnabled(true);
		MainFrame.getMenu().getToolsMenu().getStopMenuItem().setEnabled(false);
		MainFrame.getToolBar().getOpenToolBarItem().setEnabled(true);
		MainFrame.getToolBar().getProcessToolBarItem().setEnabled(true);
		MainFrame.getToolBar().getStopToolBarItem().setEnabled(false);
	}

	/**
	 * Stops the currently executing thread after the current
	 * log file has been processed.
	 */
	public void stopThread() {
		stopThread = true;
	}

	/**
	 * Memory status dump.
	 * 
	 * @param cycle cycle number.
	 */
	private void mem_status(int cycle) {
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
		log.getDebugObj().getAppLogger().debug("cycle " + cycle);
		log.getDebugObj().getAppLogger().debug("   heap: " + bean.getHeapMemoryUsage());
		log.getDebugObj().getAppLogger().debug("nonheap: " + bean.getNonHeapMemoryUsage());
	}
	
}
