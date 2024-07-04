package com.springmvc.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Book;
import com.springmvc.model.Order;
import com.springmvc.model.OrderProduct;
import com.springmvc.model.Pair;
import com.springmvc.model.ResponseJSON;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.CustomUserDetailsService;
import com.springmvc.service.impl.MediaService;
import com.springmvc.service.impl.OrderService;

@Controller
@RequestMapping(value = "/user/order")
public class UserOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	
	@GetMapping
	public ModelAndView orderPage(@RequestParam(required = false) Integer id, HttpServletRequest request) {
		ModelAndView mav;
		
		try {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				String username = (String) session.getAttribute("username");
				
				if (id != null) {
					mav = new ModelAndView("user/order-detail");
					
					Order order = orderService.getOrderOfUserById(id, username);
					List<Pair<Book, Integer>> orderProducts = orderService.getAllOrderProduct(order.getId());
					UserDetailsCustom user = userService.findUserByUsername(username);
					
					if (order != null)
						mav.addObject("order", order);
					
					if (orderProducts != null && !orderProducts.isEmpty())
						mav.addObject("orderProducts", orderProducts);
					
					if (user != null)
						mav.addObject("user", user);
					
				} else {
					mav = new ModelAndView("user/order");
					mav.addObject("orders", orderService.getAllOrderOfUser(username));				
				}
				
				mav.addObject("orderService", orderService);
				mav.addObject("mediaService", mediaService);
				
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping(value = "/cache")
	public ResponseEntity<?> cacheOrder(HttpServletRequest request, @RequestBody String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		
		if (!json.has("cartProducts"))
			return ResponseJSON.badRequest("Missing data from client");
		
		try {
			List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
			
			JSONArray jsonArray = json.getJSONArray("cartProducts");
			
			for (int i = 0; i < jsonArray.length(); ++i) {
				JSONObject cartProduct = jsonArray.getJSONObject(i);
				
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setBookId((Integer) cartProduct.getInt("bookId"));
				orderProduct.setQuantity((Integer) cartProduct.getInt("quantity"));
				
				orderProducts.add(orderProduct);
			}
			
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				session.setAttribute("orderProducts", orderProducts);
				
				return ResponseJSON.ok("Save order cache success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseJSON.badRequest("Save order cache failure");
	}
}
