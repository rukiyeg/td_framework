package fr.afcepf.dja.mvc2.util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.afcepf.dja.mvc2.exceptions.ErrorCode;
import fr.afcepf.dja.mvc2.exceptions.Mvc2Exceptions;

public class ParserXML {

	private static String ACTION = "action";
	private static String ACTION_NAME = "action-name";
	private static String URL_PATTERN = "url-pattern";
	private static String FORM = "form";
	private static String FORM_NAME = "form-name";
	private static String FORM_CLASS = "form-class";

	private Map<String, Class> actionMap = new HashMap<String, Class>();
	private Map<String, Class> actionFormMap = new HashMap<String, Class>();

	public ParserXML() {

	}

	public void parseXML(URL path) throws Mvc2Exceptions {

		if(path == null) {
			}
		Map<String, Class> map = new HashMap<String, Class>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(path.toString());

			Element racine = document.getDocumentElement();
			NodeList forms = racine.getElementsByTagName(FORM);
			for (int i = 0; i < forms.getLength(); i++) {
				Element elementForm = (Element) forms.item(i);
				String formName = elementForm.getElementsByTagName(FORM_NAME).item(0).getTextContent();
				Class formClasse = Class.forName(elementForm.getElementsByTagName(FORM_CLASS).item(0).getTextContent());
				map.put(formName, formClasse);
			}

			NodeList actions = racine.getElementsByTagName(ACTION);
			for (int k = 0; k < actions.getLength(); k++) {
				Element elementAction = (Element) actions.item(k);
				String url = elementAction.getElementsByTagName(URL_PATTERN).item(0).getTextContent();
				Class actionClasse = Class.forName(elementAction.getElementsByTagName(ACTION_NAME).item(0).getTextContent());
				actionMap.put(url, actionClasse);

				if (!elementAction.getElementsByTagName(FORM_NAME).item(0).getTextContent().isEmpty()) {
					actionFormMap.put(url, map.get(elementAction.getElementsByTagName(FORM_NAME).item(0).getTextContent()));
				}
			}

		} catch (ParserConfigurationException | DOMException| SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new Mvc2Exceptions(ErrorCode.FILE_NOT_FOUND, "file does not exist");
		} catch (ClassNotFoundException e) {
			throw new Mvc2Exceptions(ErrorCode.CLASS_NOT_FOUND, "one or more class does not exist");
		} 

	}

	public Map<String, Class> getActionMap() {
		return actionMap;
	}

	public void setActionMap(Map<String, Class> actionMap) {
		this.actionMap = actionMap;
	}

	public Map<String, Class> getActionFormMap() {
		return actionFormMap;
	}

	public void setActionFormMap(Map<String, Class> actionFormMap) {
		this.actionFormMap = actionFormMap;
	}

}
