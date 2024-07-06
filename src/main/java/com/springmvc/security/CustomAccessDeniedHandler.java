package com.springmvc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private String BASE_URL = "/bookstore";

	@Override
	public void handle(HttpServletRequest request, 
					   HttpServletResponse response,
					   AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (request.getRequestURI().startsWith(BASE_URL + "/admin"))
			response.sendRedirect(BASE_URL + "/login");
		else
			response.sendRedirect(BASE_URL + "/");
	}
}