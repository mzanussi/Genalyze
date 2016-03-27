package com.michaelzanussi.genalyze.property;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * The Genalyze property manager (as singleton).
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public final class GenalyzePropertyManager {

	private static GenalyzePropertyManager instance = new GenalyzePropertyManager();
	
	private String name;
	private String version;
	private boolean debug;
	private boolean storeUserData;
	private boolean outputToFile;
	private String outputDirectory;
	private String logDirectory;
		
	/**
	 * Returns a single instance of the property manager.
	 * 
	 * @return a single instance of the property manager.
	 */
	public static GenalyzePropertyManager getInstance() {
		return instance;
	}
	
	/**
	 * No-arg constructor.
	 */
	private GenalyzePropertyManager() {
		
		PropertyResourceBundle props = (PropertyResourceBundle)ResourceBundle.getBundle("resources.genalyze");
		
		try {
			name = props.getString("Name");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: Name");
			name = "Genalyze";	// default
		}
		
		try {
			version = props.getString("Version");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: Version");
			version = "???";	// default
		}
		
		try {
			debug = Boolean.parseBoolean(props.getString("Debug"));
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: Debug");	// default
			debug = false;
		}

		try {
			storeUserData = Boolean.parseBoolean(props.getString("StoreUserData"));
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: StoreUserData");	// default
			storeUserData = false;
		}

		try {
			outputToFile = Boolean.parseBoolean(props.getString("OutputToFile"));
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: OutputToFile");	// default
			outputToFile = false;
		}

		try {
			outputDirectory = props.getString("OutputDirectory");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: OutputDirectory");
			outputDirectory = "/tmp";
		}
		
		try {
			logDirectory = props.getString("LogDirectory");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genalyze resource: LogDirectory");
			logDirectory = "/logs";
		}
		
	}
	
	/**
	 * Returns the application name.
	 * 
	 * @return the application name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the application version.
	 * 
	 * @return the application version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Debug mode?
	 * 
	 * @return <code>true</true> if in debug mode; otherwise,
	 * <code>false</code>.
	 */
	public boolean isDebug() {
		return debug;
	}
	
	/**
	 * Store userdata in memory?
	 * 
	 * @return <code>true<code> to store userdata in memory; otherwise,
	 * <code>false</code>.
	 */
	public boolean isStoreUserData() {
		return storeUserData;
	}
	
	/**
	 * Send output to file?
	 * 
	 * @return <code>true</code> to send output to file; otherwise,
	 * <code>false</code>.
	 */
	public boolean isOutputToFile() {
		return outputToFile;
	}

	/**
	 * Returns the name of the output directory.
	 * 
	 * @return the name of the output directory.
	 */
	public String getOutputDirectory() {
		return outputDirectory;
	}
	
	/**
	 * Returns the location of the log files to process.
	 * 
	 * @return the location of the log files to process.
	 */
	public String getLogDirectory() {
		return logDirectory;
	}
	
}
