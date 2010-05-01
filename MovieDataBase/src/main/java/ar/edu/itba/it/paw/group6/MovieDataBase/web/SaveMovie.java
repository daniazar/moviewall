package ar.edu.itba.it.paw.group6.MovieDataBase.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.OnDemandGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;

import java.io.*;
import java.sql.Date;


public class SaveMovie extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String aux;
		String title = "";
		String director = "";
		String sinopsys = "";
		String imgUrl = "";
		Date release = null;
		int duration = 0;
		int id = -1;
		
		if ( (aux = req.getParameterValues("id")[0]) != null){
			
			try {
				id = new Integer(aux);
			} catch (NumberFormatException e) {
				id = -1;
			}
		}
		
		if ( (aux = req.getParameterValues("title")[0]) != null){
			title = aux;
		}
		
		if ( (aux = req.getParameterValues("director")[0]) != null){
			director = aux;
		}
		
		if ( (aux = req.getParameterValues("imgurl")[0]) != null){
			imgUrl = aux;
		}
		
		if ( (aux = req.getParameterValues("duration")[0]) != null){
			try {
				duration = new Integer(aux);
			} catch (NumberFormatException e) {
				id = 0;
			}
		}
		
		if ( (aux = req.getParameterValues("release")[0]) != null){
			try {
				release = Date.valueOf(aux);
			} catch (Exception e) {
				release = new Date(0);
			}
		}
		
		if ( (aux = req.getParameterValues("sinopsys")[0]) != null){
			sinopsys = aux;
		}
		
		long d = new java.util.Date().getTime();
		Date creation = new Date(d); 
		
		Movie movie = new Movie(id, title, director, imgUrl, duration, release, sinopsys, creation);
		MovieDao manager = DatabaseManagerFactory.getInstance().getMovieManager();
		manager.saveMovie(movie);

		// Here the movie has id:

		if (movie.getId() != 0) {
			DatabaseManagerFactory.getInstance().getMovieManager()
					.deleteMovieGenres(movie);
			String[] genres = null;
			if ((genres = req.getParameterValues("genres[]")) == null) {
				genres = req.getParameterValues("genres");
			}
			if (genres != null) {
				for (String name : genres) {
					Genre genero = new OnDemandGenre(name);
					DatabaseManagerFactory.getInstance().getMovieManager()
							.addGenreToMovie(genero, movie);
				}
			}
		}

		resp.sendRedirect("movies");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
