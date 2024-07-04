package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Book;
import com.springmvc.model.Order;
import com.springmvc.model.OrderProduct;
import com.springmvc.model.Pair;

public interface IOrderService {
	public List<Order> getAllOrderOfUser(String username);
	public List<Order> getAllOrder();
	public Order getById(int orderId);
	public Order getOrderOfUserById(int orderId, String username);
	public Book getFirstProduct(int orderId);
	public List<Pair<Book, Integer>> getAllOrderProduct(int orderId);
	public int insertOrder(Order order);
	public int updateOrder(Order order);
	public boolean deleteOrder(int orderId);
	public int insertOrderProduct(OrderProduct orderProduct);
	
	public List<Order> searchOrderByUsername(String username);
}
