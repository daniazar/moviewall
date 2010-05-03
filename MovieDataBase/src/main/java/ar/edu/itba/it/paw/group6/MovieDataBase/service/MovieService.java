package ar.edu.itba.it.paw.group6.MovieDataBase.service;


import java.util.Set;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Movie;

public interface MovieService {
	
	public boolean deleteMovie(int id);
	public boolean deleteMovie(Movie movie);
	public void saveMovie(Movie movie);
	
	public Movie getMovie(int id);
	public Iterable<Movie> getAllMovies();
	public Iterable<Movie> getMovie(String name);
	public Iterable<Movie> getMoviesByRanking();
	public Iterable<Movie> getBefore();
	public Iterable<Movie> getMoviesByModification();

	public Iterable<Movie> getMoviesByGenre(Genre genre);
	public Set<Genre> GetMovieGenres (Movie movie);
	public void deleteMovieGenres(Movie movie);
	public void addGenreToMovie(Genre genre, Movie movie);
	
	public Iterable<Movie> searchWith(String q);

}
