package ar.edu.itba.it.paw.group6.MovieDataBase.domain;


public class MovieGenre extends OnDemandGenre implements Genre {

	Boolean belongs;
	
	public MovieGenre(String name) {
		super(name);
		this.belongs = false;
	}
	
	
	public void addMovieGenreTag() {
		this.belongs = true;
		return;		
	}
	
	
	public void deleteMovieGenreTag() {
		this.belongs = false;
		return;
	}
	
	
	public boolean getMovieGenreTag() {
		return belongs;
	}
}
