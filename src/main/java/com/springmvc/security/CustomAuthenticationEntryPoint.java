package com.springmvc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private String BASE_URL = "";

    @Override
    public void commence(HttpServletRequest request, 
    					 HttpServletResponse response, 
    					 AuthenticationException authException) throws IOException, ServletException {
    	
    	if (request.getRequestURI().startsWith(BASE_URL + "/admin"))
			response.sendRedirect(BASE_URL + "/login");
		else
			response.sendRedirect(BASE_URL + "/");
    }
}