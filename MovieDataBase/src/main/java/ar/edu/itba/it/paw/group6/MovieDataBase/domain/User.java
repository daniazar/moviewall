package ar.edu.itba.it.paw.group6.MovieDataBase.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;

/*
 * Representa un usuario de la aplicaci�n
 * */

public class User {

	private String email;
	private String username;
	private String password;
	private Date dateBirth;
	private String name;
	private String surname;
	private boolean isVip;
	private boolean isAdmin;
	private boolean isNew;
	ManagerFactory factory;
	
	public User(String username, String password, String email, String name, String surname, Boolean isVip, Boolean isAdmin, Date birthday) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.isVip = isVip;
		this.isAdmin = isAdmin;
		this.dateBirth = birthday;
		/*Cuando creo un usuario por default es nuevo, si estoy volviendo de una base de datos, 
		 * llamo a setNotNew para poner este campo en falso*/
		this.isNew = true;
		this.factory = DatabaseManagerFactory.getInstance();
	}		
	
	public String getEmail() {		
		return this.email;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}
	
	public boolean passwordMatch(String password) {
	
		return this.password.equals(password);
	}

	public String regeneratePassword() {

		this.password="1234";
		return this.password;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public boolean setPassword(String newPass, String oldPass) {
		
		if( passwordMatch(oldPass)){
			this.password = newPass;
			return true;
		}	
		else
			return false;
	}
	
	 
	public void setVip(boolean vip) {
		 
		isVip=vip;
	}

	 
	public void setAdmin(boolean admin) {
		
		isAdmin=admin;
	}
	
	public int getAge() {
		//Para que esto ande deberia venir el GregorianCalendar en lugar de Date para dateBirth
		
		// Create a calendar object with the date of birth
		/*Calendar dateOfBirth = new GregorianCalendar(1972, Calendar.JANUARY, 27);
		// Create a calendar object with today's date
		Calendar today = Calendar.getInstance();

		// Get age based on year
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		// Add the tentative age to the date of birth to get this year's birthday
		dateOfBirth.add(Calendar.YEAR, age);

		// If this year's birthday has not happened yet, subtract one from age
		if (today.before(dateOfBirth)) {
		    age--;
		}*/
		
		//Date today = new Date();
		
		//today.getTime();
		
		long d = new java.util.Date().getTime();
		long m =dateBirth.getTime();
		long l=d-m;
		java.util.Date age = new java.util.Date(l);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return (new Integer(formatter.format(age))).intValue()-1970;
	}

	public Date getBirthday() {

		return this.dateBirth;
	}

	public String getName() {

		return this.name;
	}

	public String getSurname() {

		return this.surname;
	}

	public boolean getisNew() {
		
		return this.isNew;
	}

	public void setBirthday(Date date) {
		
		this.dateBirth = date;
	}

	public void setName(String name) {
		
		this.name=name;
	}

	public void setSurname(String surname) {
		
		this.surname = surname;
	}

	public boolean getisVip(){
		
		return this.isVip;
	}
	
	public boolean getisAdmin(){
		return this.isAdmin;
	}
	
	 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public List<Comment> getComments(){

		CommentDao manager = factory.getCommentManager();
		return  (List<Comment>) manager.getComments(this);
	}
	
	public int getCantComments(){
		
		CommentDao manager = factory.getCommentManager();
		return manager.getUserCantComments(this.username);
	}

	public String  getStringisvip(){
		return (this.isVip?"Yes":"No");
	}
	
	 
	public void setNotNew() {
		this.isNew=false;
	}

	 
	public boolean isNew() {
		return this.isNew;
	}
}
