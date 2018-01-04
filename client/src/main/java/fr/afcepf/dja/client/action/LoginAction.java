package fr.afcepf.dja.client.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.dja.mvc2.interfaces.IAction;
import fr.afcepf.dja.mvc2.interfaces.IActionForm;

public class LoginAction implements IAction {

	@Override
	public String execute(HttpServletResponse response, HttpServletRequest request, IActionForm actionForm) {
		String forward = "";
			
			if (!actionForm.validateForm()) {
				forward = "login.jsp";
			} else {
				forward = (request.getParameter("username").equals("toto") && 
						request.getParameter("password").equals("toto123")) ? "gagne.jsp" : "perdu.jsp";
			}

		return forward;
	}

}
