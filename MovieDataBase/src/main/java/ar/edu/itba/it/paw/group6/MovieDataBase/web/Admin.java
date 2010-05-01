package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.UserManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database.DatabaseUserManager;


public class Admin extends HttpServlet {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private UserManager manager = DatabaseUserManager.getInstance();
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setAttribute("users", manager.getAll());
			req.getRequestDispatcher("/admin/admin.jsp").forward(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
	}

	

