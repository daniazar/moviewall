package ar.edu.itba.it.paw.grupo6.MovieDataBase.service;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public interface UserService {

	//public User getLoguedUser(HttpServletRequest req);
	
	public User authenticate(String username, String password);
	
	public void saveUser(User user);
	
	public User getUser(String username);
	
	public boolean removeUser(User user);
	
	public boolean removeUser(String username);
	
	public Iterable<User> getAll();
	
	public Iterable<User> searchWith(String q);
	
}
