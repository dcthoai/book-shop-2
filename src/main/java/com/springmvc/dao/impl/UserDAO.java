package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.IUserDAO;
import com.springmvc.mapper.MapperUser;
import com.springmvc.model.UserDetailsCustom;

@Repository
public class UserDAO extends AbstractDAO<UserDetailsCustom> implements IUserDAO {

	@Override
	public int insert(UserDetailsCustom user) {
		String sql = "INSERT INTO `bookstore`.`user` (`username`, `password`, `email`, `role`, `createdBy`) VALUES (?, ?, ?, ?, ?)";
		
		int userId = executeInsert(sql, user.getUsername(),
										user.getPassword(),
										user.getEmail(),
										user.getRole(),
										user.getCreatedBy());
		
		return userId;
	}

	@Override
	public int update(UserDetailsCustom user) {
		String sql = "UPDATE `bookstore`.`user` SET `email` = ?, "
												+ "`password` = ?, "
												+ "`phone` = ?, "
												+ "`address` = ?, "
												+ "`token` = ?, "
												+ "`fullname` = ?, "
												+ "`modifiedBy` = ? WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, user.getEmail(), 
											  user.getPassword(),
											  user.getPhone(),
											  user.getAddress(),
											  user.getToken(),
											  user.getFullname(),
											  user.getModifiedBy(),
											  user.getId());
		
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `bookstore`.`user` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}

	@Override
	public UserDetailsCustom getById(int id) {
		String sql = "SELECT * FROM `bookstore`.`user` WHERE (`id` = ?)";
		
		List<UserDetailsCustom> listUsers = executeQuery(sql, new MapperUser(), id);
		
		return listUsers.isEmpty() ? null : listUsers.get(0);
	}

	@Override
	public UserDetailsCustom findUserByUsername(String username) {
		String sql = "SELECT * FROM `bookstore`.`user` WHERE (`username` = ?)";
		
		List<UserDetailsCustom> listUsers = executeQuery(sql, new MapperUser(), username);
		
		return listUsers.isEmpty() ? null : listUsers.get(0);
	}

	@Override
	public UserDetailsCustom findUserByEmail(String email) {
		String sql = "SELECT * FROM `bookstore`.`user` WHERE (`email` = ?)";
		
		List<UserDetailsCustom> listUsers = executeQuery(sql, new MapperUser(), email);
		
		return listUsers.isEmpty() ? null : listUsers.get(0);
	}

	@Override
	public List<UserDetailsCustom> getAllUsers() {
		String sql = "SELECT * FROM `bookstore`.`user`";
		
		List<UserDetailsCustom> listUsers = executeQuery(sql, new MapperUser());
		
		return listUsers;
	}
}