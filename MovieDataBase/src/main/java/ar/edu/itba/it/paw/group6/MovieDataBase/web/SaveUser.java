package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.UserDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public class SaveUser extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//MovieManager manager = DatabaseManagerFactory.getInstance().getMovieManager();
		String aux=null;
		String username=null;
		String password=null;
//		String repassword=null;
		String email = null;
		String name=null;
		String surname=null;
		Date date=null;
		
		
		if ( (aux = req.getParameterValues("username")[0]) != null){
			username = aux;
		}
		
		UserDao manager = DatabaseManagerFactory.getInstance().getUserManager();
		if (manager.getUser(username) == null) 
		{
				if ( (aux = req.getParameterValues("password")[0]) != null){
					password = aux;
				}
				
		//		if ( (aux = req.getParameterValues("repassword")[0]) != null){
		//			repassword = aux;
		//		}
				
				if ( (aux = req.getParameterValues("email")[0]) != null){
					email = aux;
				}
				
				if ( (aux = req.getParameterValues("name")[0]) != null){
					name = aux;
				}
				
				if ( (aux = req.getParameterValues("surname")[0]) != null){
					surname = aux;
				}
		
				if ( (aux = req.getParameterValues("date")[0]) != null){
					try {
							date = Date.valueOf(aux);
					} catch (Exception e) {
							date = new Date(0);
					}
			}
			
			boolean isVip = false;
			boolean isAdmin = false;
	
			User user = new User(username, password, email, name, surname, isVip, isAdmin, date);
	
			manager.saveUser(user);
			//
			
			req.getSession().setAttribute("mierror","Registration OK");
			resp.sendRedirect("../login");
			//req.setAttribute("error", "Registration OK!");
			//req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			//resp.sendRedirect("../registerok");
		
		}else{
		resp.sendRedirect("../register");
		}
		//Pagina que diga que se registro correctamente
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
