/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments;

import java.sql.Date;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.SiteUser;


public interface Comment {

	/**
	 * De que usuario es el comentario.
	 * @return SIteUser
	 */
	public SiteUser getUser();
	
	public String getUserId();
	
	public int getMovieId();
	
	
	public int getId();
	/**
	 * Retorna la pelicula sobre la que se comento 	
	 */
	public Movie getMovie();
	
	/**
	 * Retorna el contenido del comentario. 
	 */
	public String getContent();
	
	/**
	 * Retorna el rating del comentario.
	 * No es float como el diagrama. 
	 */
	public int getRating();
	
	/**
	 * si el comentario es nuevo y no tiene persistencia.
	 * @return boolean
	 */
	public boolean IsNew();
	
	/**
	 * Obtiener la fecha del comentario.
	 * @return Date
	 */
	public Date getDate();
	
}
