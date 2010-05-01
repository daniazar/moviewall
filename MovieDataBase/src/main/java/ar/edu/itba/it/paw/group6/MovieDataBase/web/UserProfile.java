/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;


public class UserProfile extends HttpServlet {

	
	/**
	 * @author dani
	 */
	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = (req.getParameter("code"));
		User user = manfact.getUserManager().getUser(userid);
		
		if( user == null )
		{
			throw new RuntimeException("User doesn't exist");
			
		}
		req.setAttribute("userp", user);	
		Iterable<Comment> com = manfact.getCommentManager().getComments(user);
		req.setAttribute("comments", com);
		req.getRequestDispatcher("/WEB-INF/jsp/userProfile.jsp").forward(req, resp);
		
	}

}
