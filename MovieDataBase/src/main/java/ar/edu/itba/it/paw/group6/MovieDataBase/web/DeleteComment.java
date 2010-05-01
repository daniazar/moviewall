package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;


public class DeleteComment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
			
			String id =req.getParameter("id");
			Comment c = manfact.getCommentManager().getComment(id);
			String userid = c.getUser().getUsername();
			User user = (User) req.getSession().getAttribute("user");
			
			if( userid.equals(user.getUsername()) || user.getisAdmin())
			{
				manfact.getCommentManager().removeComment(c);
				resp.sendRedirect(req.getHeader("referer"));
				
				
			}
			else {
				
				req.setAttribute("error", "You are only able to delete comments if you are admin or the creator of it.");
				resp.sendRedirect(req.getHeader("referer"));
				
			}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
