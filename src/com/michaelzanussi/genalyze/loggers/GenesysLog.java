package com.michaelzanussi.genalyze.loggers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.michaelzanussi.genalyze.genesys.attributes.Attribute;
import com.michaelzanussi.genalyze.genesys.call.Call;
import com.michaelzanussi.genalyze.genesys.debug.Debco;
import com.michaelzanussi.genalyze.genesys.factory.AbstractGenesysFactory;
import com.michaelzanussi.genalyze.genesys.factory.GenesysFactory;
import com.michaelzanussi.genalyze.genesys.messages.Message;
import com.michaelzanussi.genalyze.genesys.statemachine.GenesysStateMachine;
import com.michaelzanussi.genalyze.genesys.statemachine.Header;
import com.michaelzanussi.genalyze.genesys.statemachine.StateException;
import com.michaelzanussi.genalyze.output.IMonitor;
import com.michaelzanussi.genalyze.ui.components.ExBox;
import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * A concrete implementation of <code>Log</code> for Genesys logs.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (28 September 2006) 
 */
public class GenesysLog implements Log, Genesys {

	/**
	 * The entire list of calls. 
	 */
	private Map<String,Call> calls;
	
	/**
	 * The current message being processed. 
	 */
	private Message message;
	
	/**
	 * The current attribute of the current message being processed.
	 */
	private Attribute attribute;
	
	/**
	 * The Genesys state machine. Instantiated in the concrete class. 
	 */
	private GenesysStateMachine gsm;
	
	private String line;			// the current log line being processed
	private String prevLine;		// holds the incomplete log line from the previous log
	
	private int line_count;
	private int file_count;
	private int message_count;
	private boolean pushback;
	
	protected String filename;
	
	private IMonitor monitor;		// here's where to dump output to
	
	private boolean debug;			// are we in debug mode?
	
	// TODO: rename these 2 vars
	//private StringBuilder linenum;
	//private StringBuilder filenum;
	
	private StringBuilder buf;		// current line being processed
	
	private Debco debco;

	/**
	 * The Genesys factory for creating objects.
	 */
	private AbstractGenesysFactory factory;
	
	/**
	 * Single-arg constructor.
	 * 
	 * @param monitor reference to monitor object.
	 */
	public GenesysLog(IMonitor monitor) {
		this.monitor = monitor;
		calls = new TreeMap<String,Call>();
		gsm = new GenesysStateMachine(this);
		factory = new GenesysFactory();
		line_count = 0;
		file_count = 0;
		message_count = 0;
		pushback = false;
		debug = false;
		//linenum = new StringBuilder();
		//filenum = new StringBuilder();
		buf = new StringBuilder();
		debco = new Debco();
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#dump(java.lang.String, java.lang.String)
	 */
	public void dump(String line, String tag) {
		
		/*if (linenum.length() > 0) {
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
		
		monitor.output("[" + filenum + "][" + linenum + "] " + tag + ": " + line, "dump");*/
		// Add debug information.
		//if (debug) System.out.println("[" + filenum + "][" + linenum + "] " + tag + ": " + line);
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#updateMessage()
	 */
	public void updateMessage() {
		// Add the current attribute to the message.
		message.addAttribute(attribute);
		// Clear the attribute.
		attribute = null;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#updateCall()
	 */
	public void updateCall() {
		// Get the connection ID for the current message.
		String connID = message.getConnID();
		
		if (connID == null) {
			// TODO: Handle no connection ID correctly.
			connID = "9999999999999999";
		}
		// Retrieve the current call from the call list.
		Call call = calls.remove(connID);
		
		// If call doesn't exist (i.e. a new ConnID), create a new call.
		if (call == null) {
			call = new Call();
			if (debug) getDebugObj().getAppLogger().debug("New call " + connID + " (" + (calls.size()+1) + ")");
		}
		
		// Update some call-specific data. Now, the message will contain
		// this information possibly and will be attached to the call
		// via the message Vector, but this will store critical
		// information at the call level rather than a message level
		// for much easier access.
		call.setConnID(connID);
		call.setDNIS(message.getDNIS());
		call.setANI(message.getANI());

		// ***********************************************************
		// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
		// ***********************************************************

		// Add support for non-standard Genesys items, such as key-value 
		// pairs in userdata. At a call level, which may contain multiple 
		// messages, we want to know if a particular userdata item has 
		// been set or not at any point during the call. Example:
		call.setEWT(message.getEWT());
		if (message.getEWT() >= 1) {
			monitor.output("EWT:\t " + message.getEWT() + "\t (" + filename + ")", "console");
		}
		
		// ***********************************************************
		// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
		// ***********************************************************
		
		// Add the message to the call then clear the message.
		call.addMessage(message);
		message = null;

		// Finally, put the call back into the map.
		calls.put(call.getConnID(), call);
		
		// Log the call.
		monitor.output(call, null);
		
		// End-of-call processing here? (of course, need to figure
		// out how to determine that this was the last message
		// for this call). At minimum, flag as call finished.
	}

	// ***********************************************************
	// Begin: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#setCustomData(com.michaelzanussi.genalyze.util.KeyValuePair)
	 */
	public void setCustomData(KeyValuePair kvp) {
		// Add checks for non-standard Genesys items, such
		// as key-value pairs in userdata.
		if ("EWT".equalsIgnoreCase(kvp.getKey())) {
			message.setEWT(kvp.getValue());
		}
	}

	// ***********************************************************
	// End: (MBZ) NON-STANDARD GENESYS CUSTOMIZATIONS [USERDATA]
	// ***********************************************************
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getNumberOfCalls()
	 */
	public int getNumberOfCalls() {
		if (calls == null) {
			return 0;
		}
		return calls.size();
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getNumberOfFiles()
	 */
	public int getNumberOfFiles() {
		return file_count;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getNumberOfLines()
	 */
	public int getNumberOfLines() {
		return line_count;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getNumberOfMessages()
	 */
	public int getNumberOfMessages() {
		return message_count;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#incrementNumberOfMessages()
	 */
	public void incrementNumberOfMessages() {
		message_count++;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getMessage()
	 */
	public Message getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#setMessage(com.michaelzanussi.genalyze.genesys.messages.Message)
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#isPushback()
	 */
	public boolean isPushback() {
		return pushback;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#setPushback(boolean)
	 */
	public void setPushback(boolean pushback) {
		this.pushback = pushback;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getStateMachine()
	 */
	public GenesysStateMachine getStateMachine() {
		return gsm;
	}
	
	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#getAttribute()
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#setAttribute(com.michaelzanussi.genalyze.genesys.attributes.Attribute)
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Log#process(java.lang.String)
	 */
	public void process(String filename) {

		this.filename = filename;
		
		BufferedReader br = null;
		pushback = false;
		line_count = 0;					// reset line count
		
		try {
			// Open the input source.
			br = new BufferedReader(new FileReader(filename));
			file_count++;
			
		    while (true) {

		    	if (pushback) {
		    		// If a pushback is requested, we want to use the 
		    		// current, existing line and not read in a new 
		    		// line from the input file.
		    		pushback = false;
		    	}
		    	else {
		    		// Empty buffer.
		    		if (buf.length() > 0) {
		    			buf.delete(0, buf.length());
		    		}
		    		// The next few checks do not apply 
		    		// while processing the header.
		    		if (!(gsm.getState() instanceof Header)) {
	    				// Do we have an incomplete line?
		    			if (prevLine != null) {
		    				buf.append(prevLine);
		    				prevLine = null;
		    			}
		    			// Check for last state, if any.
		    			if (gsm.getLastState() != null) {
		    				// Pickup where we left off in the last log.
			    			gsm.setState(gsm.getLastState());
			    			gsm.setLastState(null);
		    			}
		    			// TODO: set var indicating a composite line?
		    		}
		    		// Read in data. Use read() so we can handle lines not
		    		// terminated with a newline (which would indicate a
		    		// split line between two log files).
		    		int ch;
		    		while ((ch = br.read()) != -1) {
		    			// Split lines do not end with a newline.
		    			if (ch == '\n') {
		    				break;
		    			}
		    			// Add character to string.
		    			buf.append((char)ch);
		    		}
		    		// Convert to a String.
		    		line = buf.toString();
			    	// Break if end of file.
			    	if (ch == -1) {
			    		// Do we have an incomplete line that needs to
			    		// be completed using the next log file?
			    		if (line.length() > 0) {
			    			prevLine = line;
			    		}
			    		// Save the current state.
			    		gsm.setLastState(gsm.getState());
			    		// We might want to use InitState rather than a
			    		// header state IF WE ARE INTERESTED IN CHECKING
			    		// THAT WE ARE PROCESSING THE CORRECT TYPE OF LOG.
			    		// This would prevent mixed-log processing. If
			    		// we do this, we should change InitState to implement
			    		// the HeaderState marker interface so that 'chunk the
			    		// log header' code runs above.
			    		//gsm.setState(gsm.getInitState());
			    		gsm.setState(gsm.getLastKnownHeaderState());
			    		break;
			    	}
			    	// Increment lines processed.
			    	line_count++;
		    	}
		    	try {
					gsm.getState().exec(line);
				} catch (StateException e) {
					e.printStackTrace();
					System.exit(0);
				}
		    }
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#showException(java.lang.Object, java.lang.String, java.lang.String)
	 */
	public void showException(Object obj, String method, String msg) {
		String str = "An exception occured in " + obj + "." + method + "().\n";
		str += "\nERROR:\n" + msg;
		str += "\n\nFILE: " + filename;
		str += "\nLINE: " + line_count;
		str += "\nDATA: " + line;
		ExBox.disp(str);
	}

	/* (non-Javadoc)
	 * @see com.michaelzanussi.genalyze.loggers.Genesys#factory()
	 */
	public AbstractGenesysFactory factory() {
		return factory;
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