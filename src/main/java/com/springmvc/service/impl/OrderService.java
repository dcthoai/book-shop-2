package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.BookDAO;
import com.springmvc.dao.impl.OrderDAO;
import com.springmvc.dao.impl.OrderProductDAO;
import com.springmvc.mapper.MapperBook;
import com.springmvc.mapper.MapperOrder;
import com.springmvc.model.Book;
import com.springmvc.model.Order;
import com.springmvc.model.OrderProduct;
import com.springmvc.model.Pair;
import com.springmvc.service.IOrderService;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private OrderProductDAO orderProductDAO;
	
	@Autowired
	private BookDAO bookDAO;
	

	@Override
	public List<Order> getAllOrderOfUser(String username) {
		String sql = "SELECT o.* FROM `order` o JOIN `user` u ON o.userId = u.id WHERE u.username = ? ";
		List<Order> orders = orderDao.executeQuery(sql, new MapperOrder(), username);
		
		return orders != null ? orders : Collections.emptyList();
	}

	@Override
	public List<Order> getAllOrder() {
		return orderDao.getAllOrder();
	}

	@Override
	public Order getById(int orderId) {
		return orderDao.getById(orderId);
	}

	@Override
	public Order getOrderOfUserById(int orderId, String username) {
		String sql = "SELECT o.* FROM `order` o JOIN `user` u ON o.userId = u.id WHERE u.username = ? AND o.id = ?";
		List<Order> orders = orderDao.executeQuery(sql, new MapperOrder(), username, orderId);
		
		return orders != null ? orders.get(0) : null;
	}

	@Override
	public Book getFirstProduct(int orderId) {
		String sql = "SELECT b.* FROM `book` b JOIN `orderproduct` op ON b.id = op.bookId "
					+ " WHERE op.orderId = ? ORDER BY op.id LIMIT 1";

		List<Book> books = bookDAO.executeQuery(sql, new MapperBook(), orderId);
		
		return books != null ? books.get(0) : null;
	}

	@Override
	public List<Pair<Book, Integer>> getAllOrderProduct(int orderId) {		
		List<OrderProduct> orderProducts = orderProductDAO.getAllOrderProduct(orderId);
		List<Pair<Book, Integer>> orderProductPairs = new ArrayList<Pair<Book, Integer>>();
		
		for (OrderProduct orderProduct : orderProducts) {
			Book book = bookDAO.getById(orderProduct.getBookId());
			
			Pair<Book, Integer> pair = new Pair<Book, Integer>(book, orderProduct.getQuantity());
			orderProductPairs.add(pair);
		}
		
		return orderProductPairs;
	}

	@Override
	public int insertOrder(Order order) {
		int orderId = orderDao.insert(order);
		
		return orderId;
	}

	@Override
	public int updateOrder(Order order) {
		int affectedRows = orderDao.update(order);
		
		return affectedRows;
	}

	@Override
	public boolean deleteOrder(int orderId) {
		int affectedRows = orderDao.delete(orderId);
		
		return affectedRows > 0;
	}

	@Override
	public int insertOrderProduct(OrderProduct orderProduct) {
		int orderProductId = orderProductDAO.insert(orderProduct);
		
		return orderProductId;
	}

	@Override
	public List<Order> searchOrderByUsername(String username) {
		String sql = "SELECT o.* FROM `order` o WHERE o.userId IN ( SELECT u.id FROM `user` u WHERE u.fullname LIKE ? )";
		
		List<Order> orders = orderDao.executeQuery(sql, new MapperOrder(), username);
		
		return orders;
	}
}
