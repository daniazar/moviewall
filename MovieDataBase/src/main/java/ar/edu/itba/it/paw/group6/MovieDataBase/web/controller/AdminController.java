package ar.edu.itba.it.paw.group6.MovieDataBase.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.UserService;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.LoginForm;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.RegisterForm;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.validator.LoginFormValidator;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.validator.RegisterFormValidator;

import org.springframework.validation.Errors;
@Controller
public class AdminController {
	
	private UserService service;
	private CommentService commService;
	private MovieService movService;
	
	@Autowired
	public AdminController(UserService service, CommentService commService, MovieService movService) {
		this.service = service;
		this.commService = commService;
		this.movService = movService;
		}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView movies(@RequestParam("q") String query) {
		ModelAndView mav = new ModelAndView();
				mav.addObject(movService.searchWith(query));
				mav.addObject("query",query);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String deletemovie(@RequestParam("movie") Movie movie, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
				movService.deleteMovie(movie);
				String redirect = "redirect:" +  req.getHeader("referer");
				
				return redirect;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView movies() {
		ModelAndView mav = new ModelAndView();
				mav.addObject(movService.getAllMovies());
				mav.addObject("query","");
		return mav;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView users() {
		ModelAndView mav = new ModelAndView();
				mav.addObject(service.getAll());
				mav.addObject("query","");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView users(@RequestParam("q") String query) {
		ModelAndView mav = new ModelAndView();
				mav.addObject(service.searchWith(query));
				mav.addObject("query",query);
				
		return mav;
	}


	@RequestMapping(method = RequestMethod.GET)
	public String userupgrade(@RequestParam("userp") User user,@RequestParam("admin") String a,@RequestParam("vip") String v, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
				boolean vip = false, admin = false;
				if (v.equals( "true"))
				{
					vip = true;
				}
				if (a.equals( "true"))
				{
					admin = true;
				}

				user.setVip(vip);
				user.setAdmin(admin);
				service.saveUser(user);
				
				
				String redirect = "redirect:" +  req.getHeader("referer");
				
				return redirect;
	}

}
