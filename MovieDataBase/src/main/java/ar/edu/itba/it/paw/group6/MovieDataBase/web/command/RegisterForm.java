package ar.edu.itba.it.paw.group6.MovieDataBase.web.command;

import java.sql.Date;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.User;

public class RegisterForm {
	
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String birthday;
	public RegisterForm() {
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
	return new User(username, password , email, name, surname, false, false,  Date.valueOf(birthday));
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
