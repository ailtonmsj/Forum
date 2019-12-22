package br.com.amsj.forum.controller.dto;

public class TokenDto {
	
	private String token;
	private String authenticationType;
	
	public TokenDto(String token, String authenticationType) {
		this.token = token;
		this.authenticationType = authenticationType;
	}
	
	public String getToken() {
		return token;
	}
	public String getAuthenticationType() {
		return authenticationType;
	}
	
	
}
