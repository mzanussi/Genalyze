package com.michaelzanussi.genalyze.loggers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.michaelzanussi.genalyze.genesys.debug.Debco;
import com.michaelzanussi.genalyze.output.IMonitor;

/**
 * An example of a concrete implementation of <code>Log</code> for a 
 * generic log.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (19 January 2007) 
 */
public class GenericLog implements Log, Generic {

	private String line;			// the current log line being processed
	
	private int line_count = 0;
	private int file_count = 0;
	private boolean pushback = false;
	
	protected String filename = null;
	
	private IMonitor monitor;		// here's where to dump output to
	
	private boolean debug;			// are we in debug mode?
	
	// TODO: rename these 2 vars
	private StringBuilder linenum;
	private StringBuilder filenum;
	
	private Debco debco;

	/**
	 * Single-arg constructor.
	 */
	public GenericLog(IMonitor monitor) {
		this.monitor = monitor;
		debug = false;
		linenum = new StringBuilder();
		filenum = new StringBuilder();
		debco = new Debco();
	}

	public void dump(String line, String no) {
		
		if (linenum.length() > 0) {
			linenum.delete(0, linenum.length());
		}
		
		if (filenum.length() > 0) {
			filenum.delete(0, filenum.length());
		}
		
		linenum.append(line_count);
		filenum.append(file_count);
		
		
		for (int j = linenum.length(); j < 6; j++) {
			linenum.insert(0, ' ');
		}
		
		for (int j = filenum.length(); j < 4; j++) {
			filenum.insert(0, ' ');
		}
		
		monitor.output("[" + filenum + "][" + linenum + "]: " + line, "dump");
	}

	public int getNumberOfFiles() {
		return file_count;
	}

	public int getNumberOfLines() {
		return line_count;
	}

	public boolean isPushback() {
		return pushback;
	}

	public void setPushback(boolean pushback) {
		this.pushback = pushback;
	}

	public void process(String filename) {

		this.filename = filename;
		
		BufferedReader br = null;
		pushback = false;
		line_count = 0;					// reset line count
		
		try {
			// Open the input source.
			br = new BufferedReader(new FileReader(filename));
			file_count++;
			
		    while ((line = br.readLine()) != null) {
		    	// Increment lines processed.
		    	line_count++;
		    	dump(line, null);
	    	}
	    	Thread.sleep(1000);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Log#isDebug()
	 */
	public boolean isDebug() {
		return debug;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Log#setDebug(boolean)
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Log#getDebugObj()
	 */
	public Debco getDebugObj() {
		return debco;
	}
	
}