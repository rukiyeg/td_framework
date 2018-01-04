package fr.afcepf.dja.mvc2.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
	
	public String execute(HttpServletResponse response, HttpServletRequest request, IActionForm actionForm);
}
