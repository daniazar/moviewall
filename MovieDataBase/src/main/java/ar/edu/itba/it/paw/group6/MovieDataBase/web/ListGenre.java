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

/**
 * @author dani
 *
 */
public class ListGenre extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = (req.getParameter("code"));
		
		if( id == null )
		{
			req.getRequestDispatcher("/WEB-INF/jsp/listgenres.jsp").forward(req, resp);	
		} else
		{
			//Genre genre = manfact.getGenreManager().getGenre(id);
			Iterable<Movie> movies = manfact.getMovieManager().getMoviesByGenre(id);
			req.setAttribute("movies", movies);	
			req.setAttribute("genre", id);	
			req.getRequestDispatcher("/WEB-INF/jsp/listmovies.jsp").forward(req, resp);
		}
		
	}

	
}
