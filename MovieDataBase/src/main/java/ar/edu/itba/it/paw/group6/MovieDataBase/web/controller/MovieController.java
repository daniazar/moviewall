package ar.edu.itba.it.paw.group6.MovieDataBase.web.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;

@Controller
public class MovieController {
	
	private MovieService service;
	private CommentService commService;
	@Autowired
	public MovieController(MovieService service, CommentService commService ) {
		this.service = service;
		this.commService = commService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView allmovies() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.getAllMovies());
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewMovie(@RequestParam("movie") Movie movie) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(movie);
		Iterable<Comment> c =commService.getComments(movie);
		mav.addObject(c);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView ranking() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.getMoviesByRanking());
		return mav;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.getMoviesByModification());
		return mav;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView genres(@RequestParam("genre") Genre genre) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.getMoviesByGenre(genre));
		mav.addObject(genre);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView search(@RequestParam("q") String  query) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(service.searchWith(query));
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView genresList() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String savecomment(@RequestParam("user") User user, @RequestParam("movie") Movie movie,@RequestParam("rating") int rating,@RequestParam("comment") String comment, HttpServletRequest req ) {
		try {
			Comment c = new Comment(movie, user, comment, new Date((new java.util.Date()).getTime()), rating);
			commService.saveComment(c);			
		} catch (Exception e) {
			;
		}		
		
		String redirect = "redirect:" +  req.getHeader("referer");
					
		return redirect;
	}
		
}
