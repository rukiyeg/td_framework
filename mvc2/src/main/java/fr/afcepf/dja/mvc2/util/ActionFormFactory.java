package fr.afcepf.dja.mvc2.util;

import java.util.HashMap;
import java.util.Map;

import fr.afcepf.dja.mvc2.exceptions.Mvc2Exceptions;
import fr.afcepf.dja.mvc2.interfaces.IActionForm;


public class ActionFormFactory {

	private static ActionFormFactory myActionFormFactory;
	private Map<String, Class> map = new HashMap<String, Class>();
	private ActionFormFactory() {
	}

	public static ActionFormFactory getInstance(ParserXML parser) throws Mvc2Exceptions{
		if(myActionFormFactory == null) {
			myActionFormFactory = new ActionFormFactory();
			myActionFormFactory.initFactory(parser);
		}
		
		return myActionFormFactory;
	}
	
	private void initFactory(ParserXML parser) throws Mvc2Exceptions {
		map = parser.getActionFormMap();
	}
	
	public IActionForm fabrique(String classe) throws InstantiationException, IllegalAccessException {
		return (IActionForm)map.get(classe).newInstance();
	}
}
