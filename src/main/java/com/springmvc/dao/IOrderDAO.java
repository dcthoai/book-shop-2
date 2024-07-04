package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Order;

public interface IOrderDAO extends IGenericDAO<Order>{
	public List<Order> getAllOrder();
}
