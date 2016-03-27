package com.michaelzanussi.genalyze.genesys.debug;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Debco {
	
	private Logger applogger;

	public long objs = 0;
	
	public Debco() {
    	// Setup the application logger.
		applogger = Logger.getLogger("genalyze");
    	PropertyConfigurator.configure("log4j.properties");
	}
	
	public Logger getAppLogger() {
		return applogger;
	}

}
