package ar.edu.itba.it.paw.group6.MovieDataBase.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.LoginForm;

@Component
public class LoginFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		LoginForm obj = (LoginForm) target;
		if (errors.getFieldErrorCount("username") == 0 && obj.getUsername() == null || obj.getUsername().length()==0) {
			errors.rejectValue("username", "empty");
		}
		if (errors.getFieldErrorCount("password") == 0 && obj.getPassword() == null || obj.getPassword().length() == 0) {
			errors.rejectValue("password", "empty");
		} 
	}
}
