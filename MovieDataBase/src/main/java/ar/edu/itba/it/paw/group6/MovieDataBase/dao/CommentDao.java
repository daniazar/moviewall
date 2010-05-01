/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.dao;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;


public interface CommentDao {

	public Comment getComment(String id);
	
	public void saveComment(Comment comment);
	
	public boolean removeComment(Comment comment);
	
	public boolean removeComment(String id);
	
	public Iterable<Comment> getComments(User user);
	
	public Iterable<Comment> getComments(Movie movie);
	
	public Iterable<Comment> getAll();

	public int getUserCantComments(String username);

	int getCantComments(int id);
}
