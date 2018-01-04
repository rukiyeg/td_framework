package fr.afcepf.dja.mvc2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.dja.mvc2.exceptions.Mvc2Exceptions;
import fr.afcepf.dja.mvc2.interfaces.IAction;
import fr.afcepf.dja.mvc2.interfaces.IActionForm;
import fr.afcepf.dja.mvc2.util.ActionFactory;
import fr.afcepf.dja.mvc2.util.ActionFormFactory;
import fr.afcepf.dja.mvc2.util.BeanPopulate;
import fr.afcepf.dja.mvc2.util.ParserXML;

public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParserXML parser = new ParserXML();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		URL path = context.getResource(getInitParameter("config"));
		try {
			parser.parseXML(path);
			
			ActionFactory actionFactory = ActionFactory.getInstance(parser);
			ActionFormFactory actionFormFactory = ActionFormFactory.getInstance(parser);
			IAction action = actionFactory.fabrique(request.getServletPath());
			IActionForm actionForm = actionFormFactory.fabrique(request.getServletPath());

			BeanPopulate beanPopulate = BeanPopulate.getInstance();
			Map<String, String[]> params = request.getParameterMap();
			beanPopulate.populateBean(actionForm, params);

			String forward = action.execute(response, request, actionForm);
			
			beanPopulate.populateRequest(request, actionForm);
			request.getRequestDispatcher(forward).forward(request, response);
		
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Mvc2Exceptions e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}

	}


}
