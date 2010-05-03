package ar.edu.itba.it.paw.group6.MovieDataBase.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.GenreService;

@Component
public class GenreConverter implements Converter<String, Genre> {


	private GenreService service;
	
	@Autowired
	public GenreConverter(GenreService service) {
		this.service = service;
	}
	
	public Genre convert(String arg0) {
		Genre genre = service.getGenre(arg0);
		return genre; 
	}
}
