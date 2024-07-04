package com.springmvc.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.springmvc.dto.OrderDTO;
import com.springmvc.model.Order;
import com.springmvc.model.ResponseJSON;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.CustomUserDetailsService;
import com.springmvc.service.impl.OrderService;

@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@GetMapping
	public ModelAndView order(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/order");
		mav.addObject("orders", orderService.getAllOrder());
		mav.addObject("userService", customUserDetailsService);
		
		return mav;	
	}
	
	@GetMapping(value = "update")
	public ModelAndView orderUpdate(HttpServletRequest request, @RequestParam("id") Integer id) {
		if (id == null)
			return null;
		
		ModelAndView mav = new ModelAndView("admin/update-order");
		mav.addObject("order", orderService.getById(id));
		
		return mav;
	}
	
	@PostMapping(value = "update")
	public ResponseEntity<?> orderUpdatePost(HttpServletRequest request,
										@RequestBody String jsonString) throws IOException, JSONException, ParseException {
		
		try {
			HttpSession session = request.getSession();
			JSONObject data = new JSONObject(jsonString);
			Integer id = (Integer) data.getInt("id");
			Order order = orderService.getById(id);
			
			if (data.has("status"))
				order.setOrderStatus(data.getString("status"));
			
			if (data.has("estimatedArrival")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Date estimatedArrival = new Date(sdf.parse(data.getString("estimatedArrival")).getTime());
	            
	            order.setEstimatedArrival(estimatedArrival);
			}
			
			if (data.has("shipperPhone"))
				order.setShipperPhone(data.getString("shipperPhone"));
			order.setModifiedBy((String) session.getAttribute("username"));
			
			int isSuccess = orderService.updateOrder(order);
			if (isSuccess > 0)
				return ResponseJSON.ok("Update order success");
			
			return ResponseJSON.serverError("Cannot update order, server error");
		} catch (JSONException e) {
			return ResponseJSON.serverError("Cannot resolve json string: " + e.getMessage());
		} catch (ParseException e) {
			return ResponseJSON.serverError("Cannot parse date format: " + e.getMessage());
		} catch (Exception e) {
			return ResponseJSON.serverError("Runtime error: " + e.getMessage());
		}
	}
	
	@PostMapping(value = "update/deliveried")
	public ResponseEntity<?> deliveried(HttpServletRequest request,
										@RequestParam("id") Integer id) throws IOException {
		HttpSession session = request.getSession();
		
		if (id == null)
			return ResponseJSON.badRequest("Missing data from client");
		
		Order order = orderService.getById(id);

		order.setOrderStatus("Đã giao hàng");
		order.setModifiedBy((String) session.getAttribute("username"));
		order.setDeliveredAt(new Timestamp(System.currentTimeMillis()));
		
		int isSuccess = orderService.updateOrder(order);
		if (isSuccess > 0)
			return ResponseJSON.ok("Update order success");
		
		return ResponseJSON.serverError("Cannot update order, server error");
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> searchOrder(HttpServletRequest request,
										@RequestParam("customer") String customer) throws IOException {
		
		if (customer == null)
			return null;
		
		List<Order> orders = orderService.searchOrderByUsername("%" + customer + "%");
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		
		for (Order order : orders) {
			if (order != null) {
				OrderDTO orderDTO = new OrderDTO();
				
				if (order.getUserId() > 0) {
					UserDetailsCustom user = customUserDetailsService.getUserById(order.getUserId());
					
					if (user != null)
						orderDTO.setUsername(user.getFullname());
				}
				
		        orderDTO.setOrderStatus(order.getOrderStatus());
		        orderDTO.setAddress(order.getAddress());
		        orderDTO.setPaymentMethod(order.getPaymentMethod());
		        orderDTO.setShipperPhone(order.getShipperPhone());
		        orderDTO.setShippingCost(order.getShippingCost());
		        orderDTO.setDiscount(order.getDiscount());
		        orderDTO.setTotalPayment(order.getTotalPayment());
		        orderDTO.setOrderDate(order.getOrderDate());
		        orderDTO.setDeliveredAt(order.getDeliveredAt());
		        orderDTO.setEstimatedArrival(order.getEstimatedArrival());
		        
		        orderDTOs.add(orderDTO);
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(orderDTOs);
		
		// Use UTF-8 to transmit data
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	 }
}
