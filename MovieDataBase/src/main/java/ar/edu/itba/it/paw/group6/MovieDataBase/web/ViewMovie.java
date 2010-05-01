/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;


public class ViewMovie extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String movieid = req.getParameter("code");
				
		int id = Integer.parseInt(movieid, 10);
		
		//Iterable<Movie> movies = manfact.getMovieManager().getMoviesByRanking();
		Movie movie = manfact.getMovieManager().getMovie(id);
		req.setAttribute("movie", movie);
		
		req.setAttribute("genres", movie.getAllMovieGenres());
		
		req.setAttribute("comments", movie.getComments());
		
		req.getRequestDispatcher("/WEB-INF/jsp/viewMovie.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
