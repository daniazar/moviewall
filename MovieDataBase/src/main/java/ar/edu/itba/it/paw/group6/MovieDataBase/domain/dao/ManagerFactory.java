package ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao;


public interface ManagerFactory {

	public MovieDao getMovieManager();

	public UserDao getUserManager();

	public GenreDao getGenreManager();

	public CommentDao getCommentManager();

}
