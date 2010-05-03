package ar.edu.itba.it.paw.group6.MovieDataBase.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;

@Component
public class MovieConverter implements Converter<String, Movie> {

	private MovieService service;
	private CommentService commService;
	
	@Autowired
	public MovieConverter(MovieService service, CommentService commService) {
		this.service = service;
		this.commService = commService;
	}
	
	public Movie convert(String arg0) {
		Movie mov = service.getMovie(Integer.valueOf(arg0));
		commService.SetCantComments(mov);
		return mov; 
	}
}
