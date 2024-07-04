package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Cart;

public class MapperCart implements RowMapper<Cart>{

	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cart cart = new Cart();
		
		cart.setId(rs.getInt("id"));
		
        cart.setUserId(rs.getInt("userId"));
        cart.setQuantity(rs.getInt("quantity"));
        
        cart.setCreatedDate(rs.getTimestamp("createdDate"));
        cart.setModifiedDate(rs.getTimestamp("modifiedDate"));
        cart.setCreatedBy(rs.getString("createdBy"));
        cart.setModifiedBy(rs.getString("modifiedBy"));
		
		return cart;
	}
}