package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Book;
import com.springmvc.model.Cart;
import com.springmvc.model.CartProduct;
import com.springmvc.model.Pair;

public interface ICartService{
	public int insertCart(Cart cart);
	public boolean updateCart(Cart cart);
	public boolean deleteCart(int cartId);
	public Cart getCartById(int cartId);
	public Cart getCartByUsername(String username);

	public CartProduct getCartProductById(int cartProductId);
	public int addCartProduct(CartProduct cartProduct);
	public boolean updateCartProduct(CartProduct cartProduct);
	public boolean deleteCartProduct(int cartProductId);
	public List<Pair<Book, CartProduct>> getAllCartProducts(int cartId);
	public int countTotalQuantityCart(int cartId);
}