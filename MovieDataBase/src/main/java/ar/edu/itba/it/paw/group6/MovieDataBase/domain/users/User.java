package ar.edu.itba.it.paw.group6.MovieDataBase.domain.users;

public interface User extends Person{

	public String getUsername();
	
	public String getEmail();
	
	public String getPassword();
	
	public boolean passwordMatch(String password);
	
	public String regeneratePassword();
	
	public boolean setPassword(String newPass, String oldPass);
	
	public void setEmail(String email);
	
	public boolean getisAdmin();
	
	public boolean getisVip();
	
	public void setAdmin(boolean admin);
	public void setVip(boolean vip);
	
	
	/*Luego de volver de la base de datos llamo a este metodo y coloco el campo isNew en falso
	 * Por default isNew va a estar en true cuando creo a un usuario
	*/
	public void setNotNew();
}
