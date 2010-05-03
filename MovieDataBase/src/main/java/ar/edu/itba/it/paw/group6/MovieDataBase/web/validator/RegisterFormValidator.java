package ar.edu.itba.it.paw.group6.MovieDataBase.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.RegisterForm;

@Component
public class RegisterFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterForm.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		RegisterForm obj = (RegisterForm) target;
		if (errors.getFieldErrorCount("username") == 0 && obj.getUsername() == null || obj.getUsername().length()==0) {
			errors.rejectValue("username", "empty");
		}
		if (errors.getFieldErrorCount("password") == 0 && obj.getPassword() == null || obj.getPassword().length() == 0) {
			errors.rejectValue("password", "empty");
		}
		if (errors.getFieldErrorCount("name") == 0 && obj.getName() == null || obj.getName().length() == 0) {
			errors.rejectValue("name", "empty");
		}
		
	}
}
