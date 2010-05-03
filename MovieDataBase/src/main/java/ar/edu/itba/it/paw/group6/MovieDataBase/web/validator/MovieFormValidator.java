package ar.edu.itba.it.paw.group6.MovieDataBase.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.MovieForm;

@Component
public class MovieFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MovieForm.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		MovieForm obj = (MovieForm) target;
		if (errors.getFieldErrorCount("title") == 0 && obj.getTitle() == null || obj.getTitle().length()==0) {
			errors.rejectValue("title", "empty");
		}
		if (errors.getFieldErrorCount("director") == 0 && obj.getDirector() == null || obj.getDirector().length() == 0) {
			errors.rejectValue("director", "empty");
		}
		if (errors.getFieldErrorCount("name") == 0 && obj.getSinopsys() == null || obj.getSinopsys().length() == 0) {
			errors.rejectValue("sinopsys", "empty");
		}
		
	}
}
