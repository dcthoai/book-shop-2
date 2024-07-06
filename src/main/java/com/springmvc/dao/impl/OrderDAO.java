package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.IOrderDAO;
import com.springmvc.mapper.MapperOrder;
import com.springmvc.model.Order;

@Repository
public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

	@Override
	public int insert(Order order) {
		String sql = "INSERT INTO `order` ("
				+ " userId, orderStatus, address, customerName, customerPhone, paymentMethod, "
				+ " shippingCost, discount, totalPayment, "
				+ " orderDate, estimatedArrival, createdBy) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int idOrder = executeInsert(sql, order.getUserId(),
										 order.getOrderStatus(),
										 order.getAddress(),
										 order.getCustomerName(),
										 order.getCustomerPhone(),
										 order.getPaymentMethod(),
										 order.getShippingCost(),
										 order.getDiscount(),
										 order.getTotalPayment(),
										 order.getOrderDate(),
										 order.getEstimatedArrival(),
										 order.getCreatedBy());
		
		return idOrder;
	}

	@Override
	public int update(Order order) {
		String sql = "UPDATE `order` SET "
				+ " orderStatus = ?, address = ?, customerName = ?, customerPhone = ?, paymentMethod = ?, "
				+ " estimatedArrival = ?, shipperPhone = ?, deliveredAt = ?, modifiedBy = ? "
				+ " WHERE id = ?";
		
		int affectedRows = executeUpdate(sql, order.getOrderStatus(),
											  order.getAddress(),
											  order.getCustomerName(),
											  order.getCustomerPhone(),
											  order.getPaymentMethod(),
											  order.getEstimatedArrival(),
											  order.getShipperPhone(),
											  order.getDeliveredAt(),
											  order.getModifiedBy(),
											  order.getId());
	
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `order` WHERE id = ?";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}

	@Override
	public Order getById(int id) {
		String sql = "SELECT * FROM `order` WHERE id = ?";
		
		List<Order> listOrders = executeQuery(sql, new MapperOrder(), id);
		
		return listOrders.isEmpty() ? null : listOrders.get(0);
	}

	@Override
	public List<Order> getAllOrder() {
		String sql = "SELECT * FROM `order`";
		
		List<Order> listOrders = executeQuery(sql, new MapperOrder());
		return listOrders;
	}
}
