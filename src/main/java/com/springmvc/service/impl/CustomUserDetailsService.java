package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.UserDAO;
import com.springmvc.model.UserDetailsCustom;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsCustom userDetails = userDAO.findUserByUsername(username);
		
		if (userDetails != null)
			return userDetails;
		
        throw new UsernameNotFoundException("User not found with username: " + username);
	}

	public UserDetailsCustom findUserByUsername(String username) {
		UserDetailsCustom user = userDAO.findUserByUsername(username);
		
		return user;
	}
	
	public UserDetailsCustom findUserByEmail(String email) {
		UserDetailsCustom user = userDAO.findUserByEmail(email);
		
		return user;
	}
	
	public boolean isExistUser(String username, String email) {
		if (findUserByUsername(username) != null)
			return true;
		
		if (findUserByEmail(email) != null)
			return true;
		
		return false;
	}
	
	public int insert(UserDetailsCustom user) {
		return userDAO.insert(user);
	}
	
	public boolean update(UserDetailsCustom user) {
		return userDAO.update(user) > 0;
	}
	
	public UserDetailsCustom getUserById(int id) {
		return userDAO.getById(id);
	}
	
	public List<UserDetailsCustom> getAllUsers(){
		return userDAO.getAllUsers();
	}
}
