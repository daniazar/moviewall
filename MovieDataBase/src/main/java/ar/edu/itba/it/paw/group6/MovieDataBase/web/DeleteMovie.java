package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;

public class DeleteMovie extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MovieDao manager = DatabaseManagerFactory.getInstance().getMovieManager();
		
		String id=req.getParameter("id");
		String retURL = req.getParameter("retURL");
		
		manager.removeMovie(Integer.valueOf(id));
		
		resp.sendRedirect(retURL);
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
