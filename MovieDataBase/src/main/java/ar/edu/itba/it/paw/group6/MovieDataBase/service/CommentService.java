/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.service;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.User;


public interface CommentService {

	public Comment getComment(String id);
		
	public void saveComment(Comment comment);
	
	public boolean removeComment(Comment comment);
	
	public boolean removeComment(String id);
	
	public Iterable<Comment> getComments(User user);
	
	public Iterable<Comment> getComments(Movie movie);
	
	public Iterable<Comment> getAll();

	public void SetCantComments(Movie movie);
	
}
