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

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;


public class Ranking extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Iterable<Movie> movies = manfact.getMovieManager().getMoviesByRanking();
		ArrayList<Movie> m= new ArrayList<Movie>();
		int i = 0;
		for (Movie movie : movies) {
			if( i < 5 ){
				m.add(movie);
			}
				i++;
		}
		req.setAttribute("movies", m);
		
		req.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
