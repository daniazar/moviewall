package ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database;

import java.sql.Date;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.OnDemandComment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.factory.ObjectFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.OnDemandGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.SiteMovie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.SiteUser;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public class DatabaseObjectFactory implements ObjectFactory {
	
	private static DatabaseObjectFactory instance;

	public static synchronized DatabaseObjectFactory getInstance() {

		if (instance == null) {
			instance = new DatabaseObjectFactory();
		}
		return instance;
	}

	@Override
	public Comment getNewComment(int movie, String user, String content,
			Date date, int raiting, int id) {

		return new OnDemandComment(movie, user, content,
			date, raiting, id);
		
	}

	@Override
	public Comment getNewComment(int movie, String user, String content,
			Date date, int raiting) {

		return new OnDemandComment(movie, user, content,
			date, raiting);
	}

	@Override
	public Genre getNewGenre(String name) {

		return new OnDemandGenre(name);
	}

	@Override
	public User getNewUser(String username, String password, String email,
			String name, String surname, Boolean isVip, Boolean isAdmin,
			Date dateBirth) {

		return new SiteUser(username, password, email,name, surname, isVip, 
			isAdmin,dateBirth);
	}

	@Override
	public Movie getNewMovie(int id, String title, String director,
			String imgUrl, int duration, Date release, String synopsis, Date creation) {
		
		return new SiteMovie(id, title, director, imgUrl, duration, release,
			synopsis, creation);
	}

	@Override
	public Movie getNewMovie(int id, String title, String director,
			String imgUrl, int duration, Date release, String synopsis) {
		
		return new SiteMovie(id, title, director, imgUrl, duration, release,
			synopsis);
	}

}
