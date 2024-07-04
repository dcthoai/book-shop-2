package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Order;

public class MapperOrder implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		
		order.setId(rs.getInt("id"));
		
        order.setUserId(rs.getInt("userId"));
        order.setAddress(rs.getString("address"));
        order.setCustomerName(rs.getString("customerName"));
        order.setCustomerPhone(rs.getString("customerPhone"));
        order.setOrderStatus(rs.getString("orderStatus"));

        order.setShippingCost(rs.getLong("shippingCost"));
        order.setDiscount(rs.getLong("discount"));
        order.setTotalPayment(rs.getLong("totalPayment"));
        
        order.setPaymentMethod(rs.getString("paymentMethod"));
        order.setShipperPhone(rs.getString("shipperPhone"));
        
        order.setOrderDate(rs.getTimestamp("orderDate"));
        order.setEstimatedArrival(rs.getDate("estimatedArrival"));
        order.setDeliveredAt(rs.getTimestamp("deliveredAt"));
        
        order.setCreatedDate(rs.getTimestamp("createdDate"));
        order.setModifiedDate(rs.getTimestamp("modifiedDate"));
        order.setCreatedBy(rs.getString("createdBy"));
        order.setModifiedBy(rs.getString("modifiedBy"));
		
		return order;
	}
}