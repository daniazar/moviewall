/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.grupo6.MovieDataBase.service.MovieService;
import ar.edu.itba.it.paw.grupo6.MovieDataBase.service.Impl.MovieServiceImpl;


public class Main extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MovieService movServ = new MovieServiceImpl(manfact.getMovieManager()); 
		Iterable<Movie> movies = movServ.getMoviesByModification();
		ArrayList<Movie> m= new ArrayList<Movie>();
		int i = 0;
		for (Movie movie : movies) {
			if( i < 5 ){
				m.add(movie);
			}
				i++;
		}
		req.setAttribute("movies", m);
		
		req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
