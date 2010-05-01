package ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers;


public interface ManagerFactory {

	public MovieManager getMovieManager();

	public UserManager getUserManager();

	public GenreManager getGenreManager();

	public CommentManager getCommentManager();

}
