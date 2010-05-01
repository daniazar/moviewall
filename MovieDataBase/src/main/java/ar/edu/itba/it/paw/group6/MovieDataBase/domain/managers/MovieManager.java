package ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers;


import java.util.Set;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;

public interface MovieManager {
	
	public boolean removeMovie(int id);
	public boolean removeMovie(Movie movie);
	public void saveMovie(Movie movie);
	
	public Movie getMovie(int id);
	public Iterable<Movie> getAllMovies();
	public Iterable<Movie> getMovie(String name);
	public Iterable<Movie> getMoviesByRanking();
	public Iterable<Movie> getBefore();
	public Iterable<Movie> getMoviesByModification();

	public Iterable<Movie> getMoviesByGenre(String genre);
	public Iterable<Movie> getMoviesByComments();
	

	public Set<Genre> GetMovieGenres (Movie movie);
	public void deleteMovieGenres(Movie movie);
	public void addGenreToMovie(Genre genre, Movie movie);
	
	public Iterable<Movie> searchWith(String q);

}
