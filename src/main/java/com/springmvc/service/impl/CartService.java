package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.CartDAO;
import com.springmvc.dao.impl.CartProductDAO;
import com.springmvc.model.Book;
import com.springmvc.model.Cart;
import com.springmvc.model.CartProduct;
import com.springmvc.model.Pair;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.ICartService;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private CartProductDAO cartProductDAO;
	@Autowired
	private BookService bookService;
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public int insertCart(Cart cart) {
		int cartId = cartDAO.insert(cart);
		
		return cartId;
	}

	@Override
	public boolean updateCart(Cart cart) {
		int affectedRows = cartDAO.update(cart);
		
		return affectedRows > 0;
	}

	@Override
	public boolean deleteCart(int cartId) {
		int affectedRowes = cartDAO.delete(cartId);
		
		return affectedRowes > 0;
	}

	@Override
	public Cart getCartById(int cartId) {
		Cart cart = cartDAO.getById(cartId);
		
		return cart;
	}
	
	@Override
	public Cart getCartByUsername(String username) {
		UserDetailsCustom user = userDetailsService.findUserByUsername(username);
		return cartDAO.getCartByUserId(user.getId());
	}

	@Override
	public int addCartProduct(CartProduct cartProduct) {
		CartProduct oldCartProduct = cartProductDAO.isExist(cartProduct);
		
		if (oldCartProduct != null && oldCartProduct.getId() > 0) {
			int newQuantity = cartProduct.getQuantity() + oldCartProduct.getQuantity();
			
			if (newQuantity > 0) {
				oldCartProduct.setQuantity(newQuantity);
				
				if (updateCartProduct(oldCartProduct)) {
					Cart cart = getCartById(oldCartProduct.getCartId());
					int totalCartQuantity = countTotalQuantityCart(cart.getId());
					
					cart.setQuantity(totalCartQuantity);
					
					if (updateCart(cart))
						return 1;
					return 0;
				} else {
					return 0;
				}
			} else {
				if (deleteCartProduct(oldCartProduct.getId()))
					return 1;
				return 0;
			}
			
		} else {
			return cartProductDAO.insert(cartProduct);
		}
	}

	@Override
	public boolean updateCartProduct(CartProduct cartProduct) {
		try {
			CartProduct oldCartProduct = cartProductDAO.getById(cartProduct.getId());
			
			if (oldCartProduct != null) {
				int newQuantity = cartProduct.getQuantity();
				
				if (newQuantity > 0) {
					oldCartProduct.setQuantity(newQuantity);
					
					int affectedRows = cartProductDAO.update(oldCartProduct);
					
					return affectedRows > 0;
				} else {
					int affectedRows = cartProductDAO.delete(oldCartProduct.getId());
					
					return affectedRows > 0;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCartProduct(int cartProductId) {
		int affectedRows = cartProductDAO.delete(cartProductId);
		
		return affectedRows > 0;
	}

	@Override
	public List<Pair<Book, CartProduct>> getAllCartProducts(int cartId) {
		List<Pair<Book, CartProduct>> cartProductPairs = new ArrayList<Pair<Book, CartProduct>>();
	    List<CartProduct> cartProducts = cartProductDAO.getAllCartProducts(cartId);

	    for (CartProduct cartProduct : cartProducts) {
	        Book book = bookService.getBookById(cartProduct.getBookId());
	        
	        cartProductPairs.add(new Pair<Book, CartProduct>(book, cartProduct));
	    }

	    return cartProductPairs;
	}
	
	@Override
	public int countTotalQuantityCart(int cartId) {
		return cartProductDAO.countTotalCartQuantity(cartId);
	}

	@Override
	public CartProduct getCartProductById(int cartProductId) {
		return cartProductDAO.getById(cartProductId);
	}
	
	public int deleteCartProductByBookId(int bookId, String username) {
		Cart cart = getCartByUsername(username);
		
		String sql = "DELETE FROM `cartproduct` WHERE(cartId = ? AND bookId = ?)";
		
		int affectedRows = cartProductDAO.executeUpdate(sql, cart.getId(), bookId);
		
		return affectedRows;
	}
}