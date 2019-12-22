package br.com.amsj.forum.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class FormLogin {
	
	private String user;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(this.getUser(), this.getPassword());
	}
}
