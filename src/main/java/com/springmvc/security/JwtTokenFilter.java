package com.springmvc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	private CustomUserDetailsService userService; 
	
	private JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}
	
	private void setAuthenticationState(HttpServletRequest request, HttpSession session, UserDetails userDetails) {
		if (userDetails != null) {
			UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            if (session == null) {
            	session = request.getSession(true);
            
            	session.setAttribute("username", userDetails.getUsername());
            	session.setAttribute("isAuthenticated", true);
            }
		}
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        
        return null;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
    								HttpServletResponse response, 
    								FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession(false);
	        
	        if (session != null && Boolean.TRUE.equals(session.getAttribute("isAuthenticated"))) {
	            String username = (String) session.getAttribute("username");
	            UserDetailsCustom userDetails = userService.findUserByUsername(username);
	            setAuthenticationState(request, session, userDetails);
	        } else {
	            String token = getJwtFromRequest(request);

	            if (token != null && jwtTokenProvider.validateToken(token)) {
	                String username = jwtTokenProvider.getUsernameFromToken(token);
	                UserDetailsCustom userDetails = userService.findUserByUsername(username);
	                
	                if (token.equals(userDetails.getToken()))
	                	setAuthenticationState(request, null, userDetails);
	            }
	        }

	        filterChain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token is invalid\"}");
            
            return;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Authenticate error: FORBIDDEN\"}");
		
            return;
		}
	}
}
