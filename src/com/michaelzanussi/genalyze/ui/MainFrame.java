package com.michaelzanussi.genalyze.ui;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableModel;

import com.michaelzanussi.genalyze.loggers.GenesysLog;
import com.michaelzanussi.genalyze.loggers.Log;
import com.michaelzanussi.genalyze.output.Monitor;
import com.michaelzanussi.genalyze.property.GenalyzePropertyManager;
import com.michaelzanussi.genalyze.ui.components.Console;
import com.michaelzanussi.genalyze.ui.components.LogDump;
import com.michaelzanussi.genalyze.ui.components.Summary;
import com.michaelzanussi.genalyze.ui.menu.Menu;
import com.michaelzanussi.genalyze.ui.models.GenesysCallTableModel;
import com.michaelzanussi.genalyze.ui.property.GUIPropertyManager;
import com.michaelzanussi.genalyze.ui.toolbar.ToolBar;

/**
 * The main frame for the Genalyze test driver (as singleton).
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1;

	private static MainFrame instance = new MainFrame();	// gui instance
	
	private static JLabel status;				// status bar

	private static Menu menu;					// menu bar
	private static ToolBar toolbar;				// toolbar
	private static Console console;				// console
	private static LogDump logdump;				// log dump
	private static Summary summary;				// summary table
	
	private static File logdir;					// the directory containing log files
	
	private static ThreadedDriver driver;		// the log driver
	private static boolean reset;				// reset flag
	
	private static Log logtype;					// type of log to process
	private static TableModel summarymodel;		// summary table model
	
	private static boolean debug;				// in debug mode?
	
	/**
	 * Returns a single instance of the GUI.
	 * 
	 * @return a single instance of the GUI.
	 */
	public static MainFrame getInstance() {
		return instance;
	}

	/**
	 * No-arg constructor. Setup the application window.
	 */
	private MainFrame() {
		super();

		// Default the log directory.
		logdir = new File(GenalyzePropertyManager.getInstance().getLogDirectory());
		// User-selectable log type and model would be nice here.
		//logtype = new GenericLog(new Monitor());
		logtype = new GenesysLog(new Monitor());
		summarymodel = new GenesysCallTableModel();
		
		// Default debug status to Genalyze property file setting.
		setDebug(GenalyzePropertyManager.getInstance().isDebug());
		
		// Load the driver. 
		driver = new ThreadedDriver(logtype);
		
		// 
		// THE APPLICATION WINDOW
		//
		
		setBounds(0, 0, 1023, 767);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setTitle(GenalyzePropertyManager.getInstance().getName() + " Test Driver" );
		ClassLoader cldr = this.getClass().getClassLoader();
		setIconImage(Toolkit.getDefaultToolkit().getImage(cldr.getResource(GUIPropertyManager.getInstance().getAppIcon())));
		setResizable(false);

		// 
		// MENUBAR
		//

		menu = new Menu();
		setJMenuBar(menu);
		
		//
		// TOOLBAR
		//
		
		toolbar = new ToolBar();
		getContentPane().add(toolbar, BorderLayout.PAGE_START);
		
		//
		// LARGE SPLITPANE
		//
		
		final JSplitPane large_sp = new JSplitPane();
		large_sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		large_sp.setDividerLocation(355);
		large_sp.setDividerSize(5);
		large_sp.setEnabled(false);
		getContentPane().add(large_sp, BorderLayout.CENTER);

		//
		// SMALL SPLITPANE
		//
		
		final JSplitPane small_sp = new JSplitPane();
		small_sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		small_sp.setDividerLocation(550);
		small_sp.setEnabled(true);
		small_sp.setOneTouchExpandable(true);
		large_sp.setTopComponent(small_sp);
		
		// 
		// SCROLLPANE (SUMMARY TABLE)
		//  

		final JScrollPane summary_scrollpane = new JScrollPane();
		small_sp.setTopComponent(summary_scrollpane);

		//
		// SCROLLPANE (CONSOLE)
		//
		
		final JScrollPane console_scrollpane = new JScrollPane();
		small_sp.setBottomComponent(console_scrollpane);
		
		// 
		// SUMMARY TABLE
		//
		
		summary = new Summary(summarymodel);
		summary_scrollpane.setViewportView(summary);
		
	    //
		// LOG DUMP
		//

		logdump = new LogDump();
		large_sp.setBottomComponent(logdump);
		
	    //
		// CONSOLE
		//

		console = new Console();
		console_scrollpane.setViewportView(console);
		
		//
		// STATUS BAR
		//

		final JPanel status_panel = new JPanel();
		status_panel.setPreferredSize(new Dimension(0, 21));
		status_panel.setMaximumSize(new Dimension(0, 0));
		status_panel.setMinimumSize(new Dimension(0, 0));
		status_panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		status_panel.setLayout(new GridLayout());
		getContentPane().add(status_panel, BorderLayout.SOUTH);
		status = new JLabel();
		status_panel.add(status);
		status.setFont(new Font("", Font.BOLD, 12));
		setStatus("Ready.");
		
		//
		// SET DEFAULTS
		//
		
		getMenu().getToolsMenu().getStopMenuItem().setEnabled(false);
		getToolBar().getStopToolBarItem().setEnabled(false);
		reset = false;
	}

	/**
	 * Returns a reference to the summary table window.
	 * 
	 * @return a reference to the summary table window.
	 */
	public static Summary getSummary() {
		return summary;
	}

	/**
	 * Returns a reference to the log dump window.
	 * 
	 * @return a reference to the log dump window.
	 */
	public static LogDump getLogDump() {
		return logdump;
	}
	
	/**
	 * Returns a reference to the console window.
	 * 
	 * @return a reference to the console window.
	 */
	public static Console getConsole() {
		return console;
	}
	
	/**
	 * Set the status bar to the passed text.
	 * 
	 * @param text the text to display on the status bar.
	 */
	public static void setStatus(String text) {
		status.setText(" " + text);
	}

	/**
	 * Sets the directory name that contains the log files to process.
	 * 
	 * @param ld the directory name to set.
	 */
	public static void setLogDirectory(File fld) {
		logdir = fld;
	}
	
	/**
	 * Returns the directory name that contains the log files to process.
	 * 
	 * @return the log directory
	 */
	public static File getLogDirectory() {
		return logdir;
	}
	
	/**
	 * Returns a reference to the menu.
	 * 
	 * @return a reference to the menu.
	 */
	public static Menu getMenu() {
		return menu;
	}

	/**
	 * Returns a reference to the toolbar.
	 * 
	 * @return a reference to the toolbar.
	 */
	public static ToolBar getToolBar() {
		return toolbar;
	}
	
	/**
	 * Reset the thread so logs can be processed again. 
	 * This is a vastly incomplete hack. 
	 */
	public static void resetThread() {
		if (!reset) {
			reset = true;
		}
	}
	
	/**
	 * Returns a reference to the driver.
	 * 
	 * @return a reference to the driver.
	 */
	public static ThreadedDriver getThread() {
		if (reset) {
			driver = new ThreadedDriver(logtype);
			reset = false;
		}
		return driver;
	}

	/**
	 * Are we in debug mode?
	 * 
	 * @return <code>true</code> if in debug mode; otherwise, <code>false</code>.
	 */
	public static boolean isDebug() {
		return debug;
	}
	
	/**
	 * Set debug mode.
	 * 
	 * @param dbg the debug mode.
	 */
	public static void setDebug(boolean dbg) {
		debug = dbg;
	}
}
