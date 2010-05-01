package ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.GenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.UserDao;


public class DatabaseManagerFactory implements ManagerFactory {
	
	private static DatabaseManagerFactory instance;

	public static synchronized DatabaseManagerFactory getInstance() {

		if (instance == null) {
			instance = new DatabaseManagerFactory();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getCommentManager()
	 */
	@Override
	public CommentDao getCommentManager() {
		return DatabaseCommentDao.getInstance();
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getGenreManager()
	 */
	@Override
	public GenreDao getGenreManager() {
		return DatabaseGenreDao.getInstance();
		
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getMovieManager()
	 */
	@Override
	public MovieDao getMovieManager() {
		return DatabaseMovieDao.getInstance();
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getUserManager()
	 */
	@Override
	public UserDao getUserManager() {
		return DatabaseUserDao.getInstance();
	}


}
