package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;

@Controller
public class GeneralController {
	
	private MovieService service;
	private CommentService commService;
	
	@Autowired
	public GeneralController(MovieService service, CommentService commService ) {
		this.service = service;
		this.commService = commService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView about() {
		return null;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewMovie(@RequestParam("movie") Movie movie) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(movie);
		mav.addObject(commService.getComments(movie));
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView ranking() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.getMoviesByRanking());
		return mav;
	}
	
}
