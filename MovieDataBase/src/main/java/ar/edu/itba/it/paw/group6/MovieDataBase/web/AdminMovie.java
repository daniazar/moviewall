package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.MovieGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;

public class AdminMovie extends HttpServlet{

	private static final long serialVersionUID = 1L;

	//private MovieManager manager = DatabaseMovieManager.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieDao manager = DatabaseManagerFactory.getInstance().getMovieManager();
		String id=req.getParameter("id");
		Movie m = manager.getMovie(Integer.valueOf(id));
		Iterable<MovieGenre> genres = m.getAllMovieGenres();
		req.setAttribute("movie", m);
		req.setAttribute("genres", genres);
		req.getRequestDispatcher("/admin/movie.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
