package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.UserManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database.DatabaseUserManager;

public class AdminUsers extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserManager manager = DatabaseUserManager.getInstance();
		
		String q = req.getParameter("q");
		
		if(q == null){
			q = "";
		}
		
		req.setAttribute("query", q);
		req.setAttribute("users", manager.searchWith(q));
		req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}