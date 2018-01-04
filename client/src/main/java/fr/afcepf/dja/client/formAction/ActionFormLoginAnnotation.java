package fr.afcepf.dja.client.formAction;

import fr.afcepf.dja.mvc2.annotations.ActionFormAnnotation;
import fr.afcepf.dja.mvc2.annotations.NotEmpty;
import fr.afcepf.dja.mvc2.annotations.Username;
import fr.afcepf.dja.mvc2.interfaces.IActionForm;
import fr.afcepf.dja.mvc2.validation.ValidatorUtil;


@ActionFormAnnotation(url = "/loginAnnotation.action")
public class ActionFormLoginAnnotation  implements IActionForm{
	@Username
	private String login;

	@NotEmpty
	private String password;

	private String error;

	@Override
	public boolean validateForm() {
		StringBuilder error = new StringBuilder();
		boolean isValid = ValidatorUtil.validateEntity(this, error);
		this.error = error.toString();
		return isValid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

}
