package com.springmvc.controller.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import com.springmvc.model.Order;
import com.springmvc.model.OrderProduct;
import com.springmvc.model.ResponseJSON;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.BookService;
import com.springmvc.service.impl.CartService;
import com.springmvc.service.impl.CustomUserDetailsService;
import com.springmvc.service.impl.MediaService;
import com.springmvc.service.impl.OrderService;

@Controller
@RequestMapping(value = "/payment")
public class UserPaymentController {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private CartService cartService;
	
	private boolean updateCartQuantity(int cartId) {
		int quantityCart = cartService.countTotalQuantityCart(cartId);
		
		Cart cart = cartService.getCartById(cartId); 
		cart.setQuantity(quantityCart);
		
		boolean isUpdateCart = cartService.updateCart(cart);
	
		return isUpdateCart;
	}
	
	@GetMapping
	public ModelAndView paymentPage(HttpServletRequest request) {
		ModelAndView mav;
		
		try {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				String username = (String) session.getAttribute("username");
				UserDetailsCustom user = userService.findUserByUsername(username);
				List<OrderProduct> orderProducts = (List<OrderProduct>) session.getAttribute("orderProducts");
				
				if (user != null) {
					mav = new ModelAndView("user/payment");
					mav.addObject("user", user);
					mav.addObject("orderProducts", orderProducts);
					mav.addObject("mediaService", mediaService);
					mav.addObject("bookService", bookService);
					
					return mav;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> paymentOrder(HttpServletRequest request, @RequestBody String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		
		if (!json.has("fullname") || !json.has("address") || !json.has("phone") || !json.has("paymentMethod"))
			return ResponseJSON.badRequest("Missing data from client");
		
		try {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				String username = (String) session.getAttribute("username");
				UserDetailsCustom user = userService.findUserByUsername(username);
				List<OrderProduct> orderProducts = (List<OrderProduct>) session.getAttribute("orderProducts");
				
				if (user != null) {
					Order order = new Order();
					
					order.setUserId(user.getId());
					order.setAddress((String) json.getString("address"));
					order.setCustomerName((String) json.getString("fullname"));
					order.setCustomerPhone((String) json.getString("phone"));
					order.setCreatedBy(username);
					order.setOrderDate(new Timestamp(System.currentTimeMillis()));
					order.setOrderStatus("Chưa thanh toán");
					order.setPaymentMethod("Tiền mặt");
					order.setTotalPayment(0);
					
					LocalDate estimatedArrival = LocalDate.now().plusDays(3);
					order.setEstimatedArrival(Date.valueOf(estimatedArrival));
					
					long totalPrice = 0;
					
					for (OrderProduct orderProduct : orderProducts) {
						Book book = bookService.getBookById(orderProduct.getBookId());
						
						totalPrice += book.getSellPrice() * orderProduct.getQuantity();
					}
					
					order.setTotalPayment(totalPrice);
					int orderId = orderService.insertOrder(order);
					
					if (orderId > 0) {
						for (OrderProduct orderProduct : orderProducts) {
							orderProduct.setOrderId(orderId);
							
							orderService.insertOrderProduct(orderProduct);
						}
						
						for (OrderProduct orderProduct : orderProducts) {
							cartService.deleteCartProductByBookId(orderProduct.getBookId(), username);
						}
						
						updateCartQuantity(cartService.getCartByUsername(username).getId());
						session.removeAttribute("orderProducts");
						
						return ResponseJSON.ok("Create order success");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseJSON.serverError("Cannot save your order");
	}
}
