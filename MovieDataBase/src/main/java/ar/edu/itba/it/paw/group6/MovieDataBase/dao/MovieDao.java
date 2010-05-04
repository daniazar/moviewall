package ar.edu.itba.it.paw.group6.MovieDataBase.dao;


import java.util.Set;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Award;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;

public interface MovieDao {
	
	public boolean removeMovie(int id);
	public boolean removeMovie(Movie movie);
	public void saveMovie(Movie movie);
	
	public Movie getMovie(int id);
	public Iterable<Movie> getAllMovies();
	public Iterable<Movie> getMovie(String name);
	
	public Iterable<Movie> getMoviesByGenre(Genre genre);
	public Iterable<Movie> getMoviesByComments();
	

	public Set<Genre> GetMovieGenres (Movie movie);
	public void deleteMovieGenres(Movie movie);
	public void addGenreToMovie(Genre genre, Movie movie);
	
	public Iterable<Movie> searchWith(String q);
	public Award getAward(int id);
	public void remove(Movie movie, Award award);
	public void add(Movie movie, Award award);
	public void delete(Award award);
	public void add(Award award);
	public Iterable<Award> getAllAwards();
	
}
