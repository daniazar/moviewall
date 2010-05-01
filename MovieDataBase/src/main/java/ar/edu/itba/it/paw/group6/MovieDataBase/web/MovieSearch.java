package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;

public class MovieSearch extends HttpServlet {

	private ManagerFactory factory = DatabaseManagerFactory.getInstance();
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		
		MovieDao manager = factory.getMovieManager();
		String q = req.getParameter("q");
		
		if(q == null)
			q = "";
		
		
		req.setAttribute("movies", manager.searchWith(q));
		req.setAttribute("query", q);
		
		try {
			req.getRequestDispatcher("/WEB-INF/jsp/searchMovies.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		doGet(req, resp);
	}
	
}
