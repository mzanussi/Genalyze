package com.michaelzanussi.genalyze.genesys.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.michaelzanussi.genalyze.genesys.attributes.Attribute;
import com.michaelzanussi.genalyze.genesys.messages.Message;
import com.michaelzanussi.genalyze.util.KeyValuePair;

/**
 * The Genesys factory interface.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (29 January 2009) 
 */
public abstract class AbstractGenesysFactory {

	// This will contain a map of Constructors. As they
	// are created, add the Constructor to the map, so
	// as to reduce forName lookups, since reflection
	// is already costly enough.
	private Map<String, Constructor> cobj;

	/**
	 * No-arg constructor.
	 */
	public AbstractGenesysFactory() {
		cobj = new HashMap<String, Constructor>();
	}
	
	/**
	 * Get a constructor from which to instantiate an object.
	 * 
	 * @param name the custructor name.
	 * @return a Constructor
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	public Constructor getConstructor(String name) throws ClassNotFoundException, NoSuchMethodException {
		// Retrieve constructor, if one exists.
		Constructor con = cobj.get(name);
		// None exist, create one.
		if (con == null) {
			// Get the class object.
			Class cls = Class.forName(name);
			// Locate the single-arg constructor.
			con = cls.getConstructor(new Class[] {String.class});
			// Add the constructor object to map.
			cobj.put(name, con);
		}
		// Return constructor.
		return con;
	}
	
	/**
	 * Use reflection to dynamically instantiate a new attribute object.
	 * 
	 * @param kvp contains the attribute to create and its value.
	 * @return the new attribute.
	 */
	public abstract Attribute createAttribute(KeyValuePair kvp) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

	/**
	 * Use reflection to dynamically instantiate a new message object.
	 * 
	 * @param msg the T-Library message to create.
	 * @return the new message.
	 */
	public abstract Message createMessage(String name) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

}
