package fr.afcepf.dja.mvc2.util;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.dja.mvc2.exceptions.Mvc2Exceptions;
import fr.afcepf.dja.mvc2.interfaces.IAction;


public class ActionFactory {

	private static ActionFactory myActionFactory;
	private Map<String, Class> map = new HashMap<String, Class>();
	
	private ActionFactory() {
		
	}
	
	public static ActionFactory getInstance(ParserXML parser) throws Mvc2Exceptions {
		if(myActionFactory == null) {
			myActionFactory = new ActionFactory();
			myActionFactory.initFactory(parser);
		}
		
		return myActionFactory;
	}
	
	private void initFactory(ParserXML parser) throws Mvc2Exceptions{
		map = parser.getActionMap();
			
	}
	
	public IAction fabrique(String url) throws InstantiationException, IllegalAccessException {
		return (IAction)map.get(url).newInstance();
	}
}
