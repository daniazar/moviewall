package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.UserManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database.DatabaseUserManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;


public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserManager manager = DatabaseUserManager.getInstance();
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = manager.authenticate(username, password);
		
		String error= req.getParameter("error");
		req.getSession().setAttribute("mierror","");
	
		
		if (user != null) {
			req.getSession().setAttribute("user", user);
			if(req.getHeader("referer").contains("login")){
				resp.sendRedirect("main");
			} 
			else
			{
				resp.sendRedirect(req.getHeader("referer"));
			}
			
			

		} else {
			req.setAttribute("username", username);
			req.setAttribute("error", "Invalid username or password");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			
			
		}
	}

	
}