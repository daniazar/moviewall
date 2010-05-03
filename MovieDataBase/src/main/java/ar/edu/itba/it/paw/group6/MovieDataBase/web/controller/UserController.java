package ar.edu.itba.it.paw.group6.MovieDataBase.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.UserService;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.LoginForm;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.RegisterForm;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.validator.LoginFormValidator;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.validator.RegisterFormValidator;
@Controller
public class UserController {
	
	private UserService service;
	private CommentService commService;
	private LoginFormValidator validatorLogin;
	private RegisterFormValidator validatorRegister;
	
	@Autowired
	public UserController(UserService service, CommentService commService, LoginFormValidator validator, RegisterFormValidator validatorRegister ) {
		this.service = service;
		this.commService = commService;
		this.validatorLogin = validator;
		this.validatorRegister = validatorRegister;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView allusers() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.getAll());
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView profile(@RequestParam("user") User user) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userp" ,user);
		mav.addObject(commService.getComments(user));
		return mav;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String login(LoginForm loginForm, Errors errors, HttpServletRequest req ) {
		validatorLogin.validate(loginForm, errors);
		if (errors.hasErrors()) {
			return null;
		}
		User user=service.authenticate(loginForm.getUsername() , loginForm.getPassword());
			if(user == null)
			{
				errors.rejectValue("username","notexist");
				return null;
			}
		
		req.getSession().setAttribute("user", user);
		String redirect = "redirect:";
		if(req.getHeader("referer").contains("login")){
			redirect += "../movie/main";
		} 
		else
		{
			redirect += req.getHeader("referer");
		}			
		return redirect;
	}
	@RequestMapping(method = RequestMethod.POST)
	public String login2(@RequestParam("username") String username,@RequestParam("password") String password, HttpServletRequest req) {
		User user=service.authenticate(username , password);
			if(user == null)
			{
				return "redirect:login";
			}
		
		req.getSession().setAttribute("user", user);
		String redirect = "redirect:";
			redirect += req.getHeader("referer");			
		return redirect;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new LoginForm());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new RegisterForm());
		return mav;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
	
	req.getSession().setAttribute("user", null);
		String redirect = "redirect:" + req.getHeader("referer");			
		return redirect;
	}


	@RequestMapping(method = RequestMethod.POST)
	public String register(RegisterForm registerForm, Errors errors, HttpServletRequest req ) {
		validatorRegister.validate(registerForm, errors);
		if (errors.hasErrors()) {
			return null;
		}
		User user=registerForm.build();
		try {
			service.newUser(user);
		} catch (Exception e) {
			errors.rejectValue("username","duplicate");
			return null;
		}	
		
		req.getSession().setAttribute("user", user);
		String redirect = "redirect:" +  "../movie/main";
					
		return redirect;
	}
}
