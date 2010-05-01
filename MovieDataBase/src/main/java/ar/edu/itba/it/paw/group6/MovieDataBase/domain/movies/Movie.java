package ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies;

import java.sql.Date;
import java.util.List;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;

public interface Movie {

	public int getId();
	
	public void setId(int id);
	
	public void setDuration (int duration);
	
	public int getDuration ();
	
	public Iterable<Comment> getComments();
	
	public void addComment(Comment comment);
	
	public void removeComment(Comment comment);
	
	public void removeComment(String id);
	
	public int getCantComments();
	
	public String getDirector();
	
	public void setDirector(String director);
	
	public void setSynopsis(String synopsis);
	
	public String getSynopsis();
	
	public String getShort();

	public Date getCreation();

	
	public void setRelease(Date release);
	
	public Date getRelease();

	public List<MovieGenre> getAllMovieGenres();
	
	public Iterable<Genre> getGenres();
	
//	public void addGenre(Genre genre);
	
	public boolean isNew();
	
	public float getRating();
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getImgUrl();
	
	public void setImgUrl(String imgUrl);	
	
}
