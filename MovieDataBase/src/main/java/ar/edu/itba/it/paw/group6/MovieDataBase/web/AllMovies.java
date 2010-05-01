/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;


public class AllMovies extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Iterable<Movie> movies = manfact.getMovieManager().getAllMovies();
		req.setAttribute("movies", movies);

		req.getRequestDispatcher("/WEB-INF/jsp/viewAllMovies.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
