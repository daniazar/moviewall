package ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.OnDemandGenre;

public class SiteMovieGenre extends OnDemandGenre implements MovieGenre {

	Boolean belongs;
	
	public SiteMovieGenre(String name) {
		super(name);
		this.belongs = false;
	}
	
	@Override
	public void addMovieGenreTag() {
		this.belongs = true;
		return;		
	}
	
	@Override
	public void deleteMovieGenreTag() {
		this.belongs = false;
		return;
	}
	
	@Override
	public boolean getMovieGenreTag() {
		return belongs;
	}
}
