package com.michaelzanussi.genalyze.loggers;

import com.michaelzanussi.genalyze.genesys.attributes.Attribute;
import com.michaelzanussi.genalyze.genesys.factory.AbstractGenesysFactory;
import com.michaelzanussi.genalyze.genesys.messages.Message;
import com.michaelzanussi.genalyze.genesys.statemachine.GenesysStateMachine;
import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * This interface defines a Genesys log file.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (19 January 2007) 
 */
public interface Genesys {

	/**
	 * Sends specified data to the log dump, if one has been defined.
	 * 
	 * @param line the data to dump
	 * @param tag a tag
	 */
	public void dump(String line, String tag);

	/** 
	 * Sets the pushback status on the current line.
	 * 
	 * @param pushback the pushback status on the current line.
	 */
	public void setPushback(boolean pushback);

	/**
	 * Returns the size of the call list.
	 * 
	 * @return the size of the call list.
	 */
	public int getNumberOfCalls();

	/**
	 * Returns the current message being processed.
	 * 
	 * @return the current message being processed.
	 */
	public Message getMessage();

	/**
	 * Sets the current message being processed.
	 * 
	 * @param message the current message being processed.
	 */
	public void setMessage(Message message);

	/**
	 * Returns the message count.
	 * 
	 * @return the message count.
	 */
	public int getNumberOfMessages();

	/**
	 * Increment the number of messages.
	 */
	public void incrementNumberOfMessages();

	/**
	 * Returns the Genesys state machine.
	 * 
	 * @return the Genesys state machine.
	 */
	public GenesysStateMachine getStateMachine();
	
	/**
	 * Add the current message to a call based on the connection ID. 
	 * It will also update various call-specific data. This method
	 * will usually be implemented in the concrete class.
	 */
	public void updateCall();
	
	/**
	 * Update the current message with the current attribute. This will
	 * typically be called for certain list attributes such as AttributeUserData.
	 * This method will usually be implemented in the concrete class. 
	 */
	public void updateMessage();
	
	/**
	 * Returns the current attribute for the current message being processed. This
	 * will typically be set only for a list attribute such as AttributeUserData.
	 * 
	 * @return the current attribute for the current message being processed.
	 */
	public Attribute getAttribute();

	/**
	 * Sets the current attribute for the current message being processed. This
	 * will typically be set only for a list attribute such as AttributeUserData.
	 * 
	 * @param attribute the current attribute for the current message being processed.
	 */
	public void setAttribute(Attribute attribute);

	/**
	 * Adds custom non-Genesys specific data encountered to the message.
	 * 
	 * @param kvp the key-value pair containing the custom data
	 */
	public void setCustomData(KeyValuePair kvp);

	/**
	 * Show an exception in a user-friendly format.
	 * 
	 * @param obj the object throwing the exception.
	 * @param method the method where the exception occurred.
	 * @param msg the exception message.
	 */
	public void showException(Object obj, String method, String msg);

	/**
	 * Returns the Genesys factory for object creation.
	 * 
	 * @return the Genesys factory for object creation.
	 */
	public AbstractGenesysFactory factory();
	
}
