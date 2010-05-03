package ar.edu.itba.it.paw.group6.MovieDataBase.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;

@Component
public class CommentConverter implements Converter<String, Comment> {


	private CommentService service;
	
	@Autowired
	public CommentConverter(CommentService service) {
		this.service = service;
	}
	
	public Comment convert(String arg0) {
		Comment comment = service.getComment(arg0);
		return comment; 
	}
}
