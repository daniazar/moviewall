package ar.edu.itba.it.paw.group6.MovieDataBase.service;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;

public interface GenreService {
	
	public Iterable<Genre> getAll();
	
	public void saveGenre(Genre genre);
	
	public boolean removeGenre(Genre genre);
	
	public boolean removeGenre(String id);
	
	public Genre getGenre(String id);

}
