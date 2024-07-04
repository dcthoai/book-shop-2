package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.IOrderProductDAO;
import com.springmvc.mapper.MapperOrderProduct;
import com.springmvc.model.OrderProduct;

@Repository
public class OrderProductDAO extends AbstractDAO<OrderProduct> implements IOrderProductDAO{

	@Override
	public int insert(OrderProduct orderProduct) {
		String sql = "INSERT INTO `orderproduct`(orderId, bookId, quantity, createdBy) Values(?, ?, ?, ?)";
		
		int orderProductId = executeInsert(sql, orderProduct.getOrderId(), 
												orderProduct.getBookId(), 
												orderProduct.getQuantity(), 
												orderProduct.getCreatedBy());
		
		return orderProductId;
	}

	@Override
	public int update(OrderProduct orderProduct) {
		String sql = "UPDATE `orderproduct` SET quantity = ? WHERE(id = ?)";
		
		int affectedRows = executeUpdate(sql, orderProduct.getQuantity(), orderProduct.getId());
		
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `orderproduct` WHERE(id = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}

	@Override
	public OrderProduct getById(int id) {
		String sql = "SELECT * FROM `orderproduct` WHERE(id = ?)";
		
		List<OrderProduct> orderProducts = executeQuery(sql, new MapperOrderProduct(), id);
		
		return orderProducts != null ? orderProducts.get(0) : null;
	}

	@Override
	public List<OrderProduct> getAllOrderProduct(int orderId) {
		String sql = "SELECT * FROM `orderproduct` WHERE(orderId = ?)";
		
		List<OrderProduct> orderProducts = executeQuery(sql, new MapperOrderProduct(), orderId);
		
		return orderProducts;
	}
}
