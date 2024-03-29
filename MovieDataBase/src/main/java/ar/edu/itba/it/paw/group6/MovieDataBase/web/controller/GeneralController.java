package ar.edu.itba.it.paw.group6.MovieDataBase.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;


@Controller
public class GeneralController {
	
	private CommentService commService;
	
	@Autowired
	public GeneralController(CommentService commService ) {
		this.commService = commService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView about() {
		return null;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String deletecomment(@RequestParam("comment") Comment comment, HttpServletRequest req) {
		
		
		User user = (User) req.getSession().getAttribute("user");
		
		if(user != null && (comment.getUser().getUsername().equals(user.getUsername()) || user.getisAdmin()))
		{
			commService.removeComment(comment);
			
		}			
		else {
			
			req.setAttribute("error", "You are only able to delete comments if you are admin or the creator of it.");
						
		}
		String redirect = "redirect:" +  req.getHeader("referer");
		System.err.println(req.getHeader("referer"));
		return redirect;
		
		
	}
	
	
}
