package ar.edu.itba.it.paw.group6.MovieDataBase.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Award;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.GenreService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.UserService;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.MovieForm;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.validator.MovieFormValidator;
@Controller
public class AdminController {
	
	private UserService service;
	private CommentService commService;
	private MovieService movService;
	private MovieFormValidator movieValidator;
	private GenreService genService;

	
	@Autowired
	public AdminController(UserService service, CommentService commService, MovieService movService, MovieFormValidator movieValidator, GenreService genService) {
		this.service = service;
		this.commService = commService;
		this.movService = movService;
		this.movieValidator= movieValidator;
		this.genService= genService;
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

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView movie(@RequestParam("movie") Movie movie) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(movie);
		mav.addObject(commService.getComments(movie));
		mav.addObject("genreList",movie.getAllMovieGenres());
		mav.addObject(new MovieForm(movie));
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView newmovie() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(genService.getAll());
		mav.addObject(new MovieForm());
		
		return mav;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String savemovie(MovieForm movieForm, Errors errors, HttpServletRequest req ) {
		movieValidator.validate(movieForm, errors);
		if (errors.hasErrors()) {
			return null;
		}
		Movie movie= movieForm.build();
		try {
			movService.saveMovie(movie);
			if (movie.getId() != 0) {
				movService.deleteMovieGenres(movie);
				String[] genres = null;
				if ((genres = req.getParameterValues("genres[]")) == null) {
					genres = req.getParameterValues("genres");
				}
				if (genres != null) {
					for (String name : genres) {
						Genre genero = genService.getGenre(name);
						movService.addGenreToMovie(genero, movie);
					}
				}
			}
		} catch (Exception e) {
			errors.reject("empty");
			return null;
		}	
		
		String redirect = "redirect:movies";
					
		return redirect;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String deleteaward(@RequestParam("award") Award award, @RequestParam("movie") Movie movie, HttpServletRequest req) {
		movService.remove(movie, award);
				return "redirect:" + req.getHeader("referer");
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public String deleteawards(@RequestParam("award") Award award, HttpServletRequest req) {
		movService.delete(award);
				return "redirect:" + req.getHeader("referer");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView awards(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(movService.getAllAwards());
	
			
				return mav;
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String newAward() {
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String newAward(@RequestParam("award") String award, HttpServletRequest req) {
		movService.add(new Award(false, 0, award));
				return "redirect:awards";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addAward(@RequestParam("award") Award award, HttpServletRequest req, @RequestParam("movie") Movie movie, @RequestParam("won") boolean won) {
		award.setWon(won);
		movService.add(movie, award);
		return "redirect:" + req.getHeader("referer");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView addAward() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(movService.getAllAwards());
		mav.addObject(movService.getAllMovies());

		
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView flagcomment() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(commService.getAllFlagged());

		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String unflagcomment(@RequestParam("comment") Comment comment, HttpServletRequest req) {
		comment.setFlag(false);
		commService.saveComment(comment);
		
		return "redirect:" + req.getHeader("referer");
	}
}
