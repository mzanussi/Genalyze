package com.michaelzanussi.genalyze.genesys.property;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * The Genesys property manager (as singleton).
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (29 January 2007) 
 */
public final class GenesysPropertyManager {

	private static GenesysPropertyManager instance = new GenesysPropertyManager();
	
	private String version;
	private String attributePackage;
	private String messagePackage;
	
	/**
	 * Returns a single instance of the property manager.
	 * 
	 * @return a single instance of the property manager.
	 */
	public static GenesysPropertyManager getInstance() {
		return instance;
	}
	
	/**
	 * No-arg constructor.
	 */
	private GenesysPropertyManager() {
		
		PropertyResourceBundle props = (PropertyResourceBundle)ResourceBundle.getBundle("resources.genesys");
		
		try {
			version = props.getString("Version");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genesys resource: Version");
			version = "???";	// default
		}
		
		try {
			attributePackage = props.getString("AttributePackage");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genesys resource: AttributePackage");
			attributePackage = "com.michaelzanussi.genalyze.genesys.attributes";	// default
		}
		
		try {
			messagePackage = props.getString("MessagePackage");
		}
		catch (MissingResourceException mre) {
			System.out.println("Missing Genesys resource: MessagePackage");
			messagePackage = "com.michaelzanussi.genalyze.genesys.messages";	// default
		}
		
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
	 * Returns the package name containing the attributes.
	 * 
	 * @return the package name containing the attributes.
	 */
	public String getAttributePackage() {
		return attributePackage;
	}
	
	/**
	 * Returns the package name containing the messages.
	 * 
	 * @return the package name containing the messages.
	 */
	public String getMessagePackage() {
		return messagePackage;
	}
	
}
