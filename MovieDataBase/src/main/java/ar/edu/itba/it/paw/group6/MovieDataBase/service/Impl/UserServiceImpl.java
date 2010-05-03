/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.service.Impl;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.UserDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dani
 *
 */
@Service
public class UserServiceImpl implements UserService {

	
	private UserDao userDao;
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public User authenticate(String username, String password) {
	
		return userDao.authenticate(username, password);
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#getAll()
	 */
	@Override
	public Iterable<User> getAll() {
		return userDao.getAll();
		}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
	@Override
	public void newUser(User user) {
		if(getUser(user.getUsername()) != null)
		{
			throw new  DuplicatedUserException();
		}
		saveUser(user);
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#removeUser(ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User)
	 */
	@Override
	public boolean removeUser(User user) {
		return userDao.removeUser(user);
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#removeUser(java.lang.String)
	 */
	@Override
	public boolean removeUser(String username) {
		return userDao.removeUser(username);
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#saveUser(ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User)
	 */
	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.grupo6.MovieDataBase.service.UserService#searchWith(java.lang.String)
	 */
	@Override
	public Iterable<User> searchWith(String q) {
		return userDao.searchWith(q);

	}

}
