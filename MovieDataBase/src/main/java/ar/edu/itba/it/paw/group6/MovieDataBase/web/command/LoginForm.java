package ar.edu.itba.it.paw.group6.MovieDataBase.web.command;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public class LoginForm {
	
	private String username;
	private String password;
	public LoginForm() {
	}
	
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public User build() {
	return null;
	}
}
