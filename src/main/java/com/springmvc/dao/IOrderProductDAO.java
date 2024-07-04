package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.OrderProduct;

public interface IOrderProductDAO extends IGenericDAO<OrderProduct>{

	public List<OrderProduct> getAllOrderProduct(int orderId);
}
