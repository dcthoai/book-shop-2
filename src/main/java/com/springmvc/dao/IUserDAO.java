package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.UserDetailsCustom;

public interface IUserDAO extends IGenericDAO<UserDetailsCustom>{
	public UserDetailsCustom findUserByUsername(String username);
	public UserDetailsCustom findUserByEmail(String email);
	public List<UserDetailsCustom> getAllUsers();
}