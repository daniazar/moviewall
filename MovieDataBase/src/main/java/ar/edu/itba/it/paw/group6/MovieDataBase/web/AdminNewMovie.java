package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.GenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;

@SuppressWarnings("serial")
public class AdminNewMovie extends HttpServlet{

	//private MovieManager manager = DatabaseMovieManager.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		GenreDao manager = DatabaseManagerFactory.getInstance().getGenreManager();
		//String id=req.getParameter("id");
		//Movie m = manager.getMovie(Integer.valueOf(id));
		Iterable <Genre> genres = manager.getAll();
		
		req.setAttribute("genres", genres);
		req.getRequestDispatcher("/admin/newmovie.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}