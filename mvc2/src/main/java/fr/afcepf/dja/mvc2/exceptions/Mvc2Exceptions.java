package fr.afcepf.dja.mvc2.exceptions;

public class Mvc2Exceptions extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	private String message;
	
	public Mvc2Exceptions(ErrorCode errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
