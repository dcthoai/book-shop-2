package com.springmvc.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Book;
import com.springmvc.model.Cart;
import com.springmvc.model.CartProduct;
import com.springmvc.model.Pair;
import com.springmvc.model.ResponseJSON;
import com.springmvc.service.impl.CartService;
import com.springmvc.service.impl.MediaService;

@Controller
@RequestMapping(value = "/user/cart")
public class UserCartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private MediaService mediaService;

	@GetMapping
	public ModelAndView shoppingCart(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("user/cart");
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			
			String username = (String) session.getAttribute("username");
			Boolean isUserAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
			
			if (username != null && isUserAuthenticated) {
				Cart cart = cartService.getCartByUsername(username);
					
				if (cart != null) {
					List<Pair<Book, CartProduct>> cartProductPairs = cartService.getAllCartProducts(cart.getId());
				 	
					mav.addObject("mediaService", mediaService);
					mav.addObject("quantityCart", cart.getQuantity());
					mav.addObject("cartProductPairs", cartProductPairs);
				}
			}
		}
		
		return mav;
	}
	
	private boolean updateCartQuantity(int cartId) {
		int quantityCart = cartService.countTotalQuantityCart(cartId);
		
		Cart cart = cartService.getCartById(cartId); 
		cart.setQuantity(quantityCart);
		
		boolean isUpdateCart = cartService.updateCart(cart);
	
		return isUpdateCart;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> addToCart(@RequestBody String jsonString, HttpServletRequest request) {
		JSONObject json = new JSONObject(jsonString);
		
		if (json.has("bookId") && json.has("quantity")) {
			int bookId = Integer.parseInt(json.getString("bookId"));
			int quantity = Integer.parseInt(json.getString("quantity"));
			
			try {
				HttpSession session = request.getSession(false);
				String username = (String) session.getAttribute("username");
				Cart cart = cartService.getCartByUsername(username);
				
				CartProduct cartProduct = new CartProduct();
				cartProduct.setCartId(cart.getId());
				cartProduct.setBookId(bookId);
				cartProduct.setQuantity(quantity);
				
				cartService.addCartProduct(cartProduct);
				updateCartQuantity(cart.getId());
				
				return ResponseJSON.ok("Add to cart success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ResponseJSON.badRequest("Add to cart failure");
	}

	@PostMapping(value = "/update")
	public ResponseEntity<?> updateCartProduct(@RequestBody String jsonString, HttpServletRequest request) {
		JSONObject json = new JSONObject(jsonString);
	
		if (json.has("cartProductId") && json.has("quantity")) {
			int cartProductId = Integer.parseInt(json.getString("cartProductId"));
			int quantity = json.getInt("quantity");
			
			try {
				HttpSession session = request.getSession(false);
				String username = (String) session.getAttribute("username");
				Cart cart = cartService.getCartByUsername(username);
				
				CartProduct cartProduct = cartService.getCartProductById(cartProductId);
				cartProduct.setQuantity(quantity);
				cartService.updateCartProduct(cartProduct);
				updateCartQuantity(cart.getId());
				
				return ResponseJSON.ok("Update cart success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ResponseJSON.badRequest("Update cart failure");
	}

	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteCartProduct(@RequestBody String jsonString, HttpServletRequest request) {

		JSONObject json = new JSONObject(jsonString);
		
		if (json.has("cartProductId")) {
			int cartProductId = Integer.parseInt(json.getString("cartProductId"));
			
			try {
				HttpSession session = request.getSession(false);
				String username = (String) session.getAttribute("username");
				Cart cart = cartService.getCartByUsername(username);
				
				cartService.deleteCartProduct(cartProductId);
				updateCartQuantity(cart.getId());
				
				return ResponseJSON.ok("Update cart success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ResponseJSON.badRequest("Update cart failure");
	}
	
	@GetMapping(value = "/quantity-cart")
	public ResponseEntity<?> getQuantityCart(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				String username = (String) session.getAttribute("username");
				Cart cart = cartService.getCartByUsername(username);
				
				if (cart != null) {
					int quantityCart = cartService.countTotalQuantityCart(cart.getId());
					return ResponseJSON.ok(quantityCart + "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseJSON.badRequest("User is not login");
	}
}