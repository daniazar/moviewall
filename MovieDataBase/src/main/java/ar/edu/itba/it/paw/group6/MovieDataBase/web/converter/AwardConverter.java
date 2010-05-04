package ar.edu.itba.it.paw.group6.MovieDataBase.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Award;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;

@Component
public class AwardConverter implements Converter<String, Award> {

	private MovieService service;
	
	@Autowired
	public AwardConverter(MovieService service) {
		this.service = service;
	}
	
	public Award convert(String arg0) {
		
		return service.getAward(Integer.parseInt(arg0)); 
	}
	
	
	
}
