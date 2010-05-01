/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;


public class Users extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Iterable<User> users = manfact.getUserManager().getAll();
		req.setAttribute("users", users);	
		req.getRequestDispatcher("/WEB-INF/jsp/listUsers.jsp").forward(req, resp);
		
	}

	
}
