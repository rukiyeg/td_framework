package fr.afcepf.dja.client.formAction;

import fr.afcepf.dja.mvc2.interfaces.IActionForm;

public class LoginFormAction implements IActionForm {

	private String username;

	private String password;
	
	private String error;

	@Override
	public boolean validateForm() {
		boolean isValid = false;
		StringBuilder builder = new StringBuilder("<ul>");
		if (username.isEmpty()) {
			this.username = "";
			builder.append("<li>Entrez un nom d'utilisateur</li> \n");
			isValid = false;
		} 
		
		if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
			this.password = "";
			builder.append("<li>Format du mot de passe invalide</li> \n");
			isValid = false;
		}
		
		builder.append("</ul>");
		error = builder.toString();
		return isValid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
