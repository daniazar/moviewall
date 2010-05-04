package ar.edu.itba.it.paw.group6.MovieDataBase.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
	  private static  String emailMsgTxt      = "";
	  private static final String emailSubjectTxt  = "A critical error ocurred";
	  private static final String emailFromAddress = "moviewallsupport@gmail.com";

	  // Add List of Email address to who email needs to be sent to
	  private static final String[] emailList = {"moviewallsupport@gmail.com"};

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);	
			emailMsgTxt = "A critical error ocurred while accesing: " + ((HttpServletRequest)request).getRequestURI().toString()+"\nException: "+ e; 
			
			Mail mail =new Mail();
			
			try {
			//	System.err.println("deberia estar por mandar el mail");
				mail.postMail(emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress);
			//	System.err.println("deberia haber salido el mail");
			} catch (Exception e2) {
				e= e2;
				System.err.println(e2);
			} 
//			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/error.jsp");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}
	
	
	
}
