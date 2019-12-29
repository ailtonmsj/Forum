package br.com.amsj.forum.controller.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.sun.istack.NotNull;

public class FormLogin {
	
	@NotNull @NotEmpty
	private String user;
	@NotNull @NotEmpty
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
