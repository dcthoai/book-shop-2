package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.CartProduct;

public class MapperCartProduct implements RowMapper<CartProduct>{

	@Override
	public CartProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartProduct cartProduct = new CartProduct();
		
		cartProduct.setId(rs.getInt("id"));
		
        cartProduct.setCartId(rs.getInt("cartId"));
        cartProduct.setBookId(rs.getInt("bookId"));
        cartProduct.setQuantity(rs.getInt("quantity"));
        
        cartProduct.setCreatedDate(rs.getTimestamp("createdDate"));
        cartProduct.setModifiedDate(rs.getTimestamp("modifiedDate"));
        cartProduct.setCreatedBy(rs.getString("createdBy"));
        cartProduct.setModifiedBy(rs.getString("modifiedBy"));
		
		return cartProduct;
	}
}