/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.database.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public class UserUpgrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseManagerFactory manfact = new DatabaseManagerFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = (req.getParameter("code"));
		User user = manfact.getUserManager().getUser(userid);
		if( user != null )
		{
			user.setNotNew();
			String a = (req.getParameter("admin"));
			String v = (req.getParameter("vip"));
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
			manfact.getUserManager().saveUser(user);

		}
		resp.sendRedirect(req.getHeader("referer"));
		
	}
}
