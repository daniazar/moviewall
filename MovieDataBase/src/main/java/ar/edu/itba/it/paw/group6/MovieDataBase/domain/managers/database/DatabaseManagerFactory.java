package ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.GenreManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.MovieManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.UserManager;


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
	public CommentManager getCommentManager() {
		return DatabaseCommentManager.getInstance();
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getGenreManager()
	 */
	@Override
	public GenreManager getGenreManager() {
		return DatabaseGenreManager.getInstance();
		
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getMovieManager()
	 */
	@Override
	public MovieManager getMovieManager() {
		return DatabaseMovieManager.getInstance();
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.ManagerFactory#getUserManager()
	 */
	@Override
	public UserManager getUserManager() {
		return DatabaseUserManager.getInstance();
	}


}
