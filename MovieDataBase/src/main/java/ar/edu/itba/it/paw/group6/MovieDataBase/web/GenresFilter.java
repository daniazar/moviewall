/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.web.command.LoginForm;


public class GenresFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	private ManagerFactory manfact = DatabaseManagerFactory.getInstance();
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req =(HttpServletRequest) request;
			if(req.getParameter("allgenres") == null)
			{
				Iterable<Genre> genres = manfact.getGenreManager().getAll();
				req.getSession().setAttribute("allgenres", genres);
				
				
			}
			chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
