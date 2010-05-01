package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;


public class SaveComment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ManagerFactory factory = DatabaseManagerFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String r = req.getParameter("rating");
			String m = req.getParameter("movieid");
			String u = req.getParameter("user");
			String c = req.getParameter("comment");
			
			if(argsAreValid(r, m, u, c)){
				
				CommentDao manager = factory.getCommentManager();
				int rating = Integer.valueOf(r);
				if(!(0 <= rating && rating <= 5))
					throw new NumberFormatException("Rating must be between 0 and 5");
				
				int movieid = Integer.valueOf(m);
									
				Comment comment = new Comment(factory.getMovieManager().getMovie(movieid), factory.getUserManager().getUser(u), c, new Date((new java.util.Date()).getTime()), rating);
				manager.saveComment(comment);
			}
			resp.sendRedirect(req.getHeader("referer"));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private boolean argsAreValid(String r, String m, String u, String c) {
		return !(r == null || m == null || u == null || c == null || 
				r.trim().compareTo("") == 0 || m.trim().compareTo("") == 0
				|| u.trim().compareTo("") == 0 || c.trim().compareTo("") == 0);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
