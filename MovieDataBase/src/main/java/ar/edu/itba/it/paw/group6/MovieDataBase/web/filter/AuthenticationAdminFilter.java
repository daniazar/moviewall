package ar.edu.itba.it.paw.group6.MovieDataBase.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public class AuthenticationAdminFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// Do nothing
	}

	public void destroy() {
		// Do nothing
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (!req.getRequestURL().toString().contains("css/style.css") && !req.getRequestURL().toString().contains("login")
				&& req.getSession().getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			
			User u = (User) req.getSession().getAttribute("user");
			
			if(u.getisAdmin()){
				chain.doFilter(request, response);
			}
			else{
	
				resp.sendRedirect(req.getContextPath() + "/main");
			}
		}
	}
}

