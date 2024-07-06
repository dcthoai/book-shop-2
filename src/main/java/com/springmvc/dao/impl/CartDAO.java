package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.ICartDAO;
import com.springmvc.mapper.MapperCart;
import com.springmvc.model.Cart;

@Repository
public class CartDAO extends AbstractDAO<Cart> implements ICartDAO{

	@Override
	public int insert(Cart cart) {
		String sql = "INSERT INTO `cart` (`userId`, `quantity`, `createdBy`) VALUES (?, ?, ?)";
		
		int cartId = executeInsert(sql, cart.getUserId(),
										cart.getQuantity(),
										cart.getCreatedBy());
		
		return cartId;
	}

	@Override
	public int update(Cart cart) {
		String sql = "UPDATE `cart` SET `userId` = ?, `quantity` = ?, `modifiedBy` = ? WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, cart.getUserId(),
											  cart.getQuantity(),
											  cart.getModifiedBy(),
											  cart.getId());
		
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `cart` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}

	@Override
	public Cart getById(int id) {
		String sql = "SELECT * FROM `cart` WHERE (`id` = ?)";
		
		List<Cart> listCarts = executeQuery(sql, new MapperCart(), id);
		
		return listCarts.isEmpty() ? null : listCarts.get(0);
	}

	@Override
	public Cart getCartByUserId(int userId) {
		String sql = "SELECT * FROM `cart` WHERE (`userId` = ?)";
		
		List<Cart> listCarts = executeQuery(sql, new MapperCart(), userId);
		
		return listCarts.isEmpty() ? null : listCarts.get(0);
	}
}
