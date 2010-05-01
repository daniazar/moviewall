package ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;

public interface MovieGenre extends Genre {

	public void addMovieGenreTag();
	public boolean getMovieGenreTag();
	public void deleteMovieGenreTag();
}
