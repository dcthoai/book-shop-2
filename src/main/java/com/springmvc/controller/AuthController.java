package com.springmvc.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dto.request.UserRequest;
import com.springmvc.model.Cart;
import com.springmvc.model.ResponseJSON;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.security.JwtTokenProvider;
import com.springmvc.service.impl.CartService;
import com.springmvc.service.impl.CustomUserDetailsService;

@RestController
public class AuthController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping(value = "/register")
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	
	@GetMapping(value = "/login")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest user, 
    									HttpServletRequest request,
    									HttpServletResponse response) {
    	
    	try {
    		// Authenticate user
            UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            HttpSession session = request.getSession(true);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("isAuthenticated", true);

            // Generate JWT token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtTokenProvider.generateToken(userDetails.getUsername());

            UserDetailsCustom userCustom = customUserDetailsService.findUserByUsername(user.getUsername());
            userCustom.setToken(jwtToken);
            
            customUserDetailsService.update(userCustom);

            // Set JWT token in response header
            response.setHeader("Authorization", "Bearer " + jwtToken);
            
            if (userCustom.getRole().equals("ROLE_ADMIN")) {
            	JSONObject json = new JSONObject();
            	json.put("success", true);
            	json.put("message", "Login success");
            	json.put("isAdmin", true);
            	return ResponseEntity.ok().body(json.toString());
            }
   
            return ResponseJSON.ok("Login success!");
        } catch (BadCredentialsException e) {
        	return ResponseJSON.badRequest("Invalid username or password!");
        } catch (LockedException e) {
        	return ResponseJSON.accessDenied("Account has been locked!");
        } catch (AuthenticationException e) {
        	return ResponseJSON.serverError("Server error");
        }
    }
	
	@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody String jsonString) {
    	JSONObject userData = new JSONObject(jsonString);
    	
    	// Check data format from client
        if (!userData.has("username") || !userData.has("email") || !userData.has("password")) {
            return ResponseJSON.badRequest("Missing required data attributes from client!");
        }
	        
        try {
        	String username = userData.getString("username");
        	String email = userData.getString("email");
        	String password = userData.getString("password");
        	
        	if (customUserDetailsService.isExistUser(username, email)) {
        		return ResponseJSON.badRequest("User has already exist!");
			} else {
				Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				
				UserDetailsCustom userDetails = new UserDetailsCustom(username, passwordEncoder.encode(password), authorities);
				userDetails.setCreatedBy(username);
				userDetails.setEmail(email);
				userDetails.setRole("ROLE_USER");
				
				int userId = customUserDetailsService.insert(userDetails);
				
				if(userId > 0) {
					Cart cart = new Cart(); 
					cart.setUserId(userId); 
					cart.setQuantity(0);
					cart.setCreatedBy(username);
					  
					int cartId = cartService.insertCart(cart);
					 
					if (cartId > 0)
						return ResponseJSON.ok("Register success!");
					return ResponseJSON.serverError("Register failure! Can not create a cart");
				}
					
				return ResponseJSON.serverError("Register failure! Can not save user to database.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			return ResponseJSON.serverError("Register failure! Server error.");
		}
    }
	
	@GetMapping(value = "/auth")
	public ResponseEntity<?> authCheck() {
		return ResponseJSON.ok("Authenticated");
	}
	
	@PostMapping(value = "/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {		
		try {
			HttpSession session = request.getSession(false);
			
	        if (session != null) {
	        	String username = (String) session.getAttribute("username");
	        	UserDetailsCustom user = customUserDetailsService.findUserByUsername(username);
	        	user.setToken(null);
	        	customUserDetailsService.update(user);
	        	
	            response.setHeader("Authorization", "");
	            session.invalidate();
	            SecurityContextHolder.getContext().setAuthentication(null);
	            
	            return ResponseJSON.ok("Logout success!");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return ResponseJSON.badRequest("Can not logout your account!");
	}
}
