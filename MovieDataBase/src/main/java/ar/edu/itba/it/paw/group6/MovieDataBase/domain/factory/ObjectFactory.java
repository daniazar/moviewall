package ar.edu.itba.it.paw.group6.MovieDataBase.domain.factory;

import java.sql.Date;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.User;

public interface ObjectFactory {

	public User getNewUser(String username, String password, String email, String name, String surname, Boolean isVip, Boolean isAdmin, Date dateBirth);
	
	public Movie getNewMovie(int id, String title, String director, String imgUrl, int duration, Date release, String synopsis, Date creation);
	
	public Movie getNewMovie(int id, String title, String director, String imgUrl, int duration, Date release, String synopsis);
	
	public Comment getNewComment(int movie, String user, String content, Date date, int raiting, int id);

	public Comment getNewComment(int movie, String user, String content, Date date, int raiting);
	
	public Genre getNewGenre(String name);

}