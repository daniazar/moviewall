package ar.edu.itba.it.paw.group6.MovieDataBase.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filtro que captura excepciones y muestra una página de error.
 */
public class ErrorFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// Do nothing
	}

	public void destroy() {
		// Do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (RuntimeException e) {
			request.setAttribute("error", e);	

			
			e.printStackTrace();
//			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/error.jsp");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
}