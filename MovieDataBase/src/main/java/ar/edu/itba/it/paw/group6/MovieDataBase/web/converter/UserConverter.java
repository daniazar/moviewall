package ar.edu.itba.it.paw.group6.MovieDataBase.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.UserService;

@Component
public class UserConverter implements Converter<String, User> {

	private UserService service;
	
	@Autowired
	public UserConverter(UserService service, CommentService commService) {
		this.service = service;
	}
	
	public User convert(String arg0) {
		
		return service.getUser(arg0); 
	}
	
	public User convert(String arg0, String arg1) {
		
		return service.authenticate(arg0, arg1); 
	}
	
	
}
