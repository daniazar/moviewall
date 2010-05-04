/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseGenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.GenreService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.Impl.GenreServiceImpl;


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
	@Autowired
	private GenreService service = new GenreServiceImpl(new DatabaseGenreDao());
	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req =(HttpServletRequest) request;
			if(req.getParameter("allgenres") == null)
			{
				Iterable<Genre> genres = service.getAll();
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
