package br.com.amsj.forum.config.validation;

public class ValidationError {
	
	private String field;
	private String message;
	
	public ValidationError(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
