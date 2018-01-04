package fr.afcepf.dja.client.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.dja.mvc2.annotations.ActionAnnotation;
import fr.afcepf.dja.mvc2.interfaces.IAction;
import fr.afcepf.dja.mvc2.interfaces.IActionForm;

@ActionAnnotation(url = "/loginAnnotation.action")
public class ActionLoginAnnotation implements IAction {

	@Override
	public String execute(HttpServletResponse response, HttpServletRequest request, IActionForm actionForm) {
		String forward;
		if (!actionForm.validateForm()) {
			forward = "loginAnnotation.jsp";
		} else {
			forward = (request.getParameter("username").equals("toto@gmail.com")
					&& request.getParameter("password").equals("toto123")) ? "gagne.jsp" : "perdu.jsp";
		}
		return forward;
	}

}
