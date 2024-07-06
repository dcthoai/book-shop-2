package com.springmvc.controller.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.ResponseJSON;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.CustomUserDetailsService;

@Controller
@RequestMapping(value = "/user")
public class UserAccountController {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ModelAndView accountPage(HttpServletRequest request) {
		ModelAndView mav;
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			String username = (String) session.getAttribute("username");
			UserDetailsCustom user = userService.findUserByUsername(username);
			
			if (user != null) {
				mav = new ModelAndView("user/account");
				mav.addObject("user", user);
				
				return mav;
			}
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @RequestBody String json) {
		JSONObject data = new JSONObject(json);
		
		if (!data.has("key") && !data.has("value"))
			return ResponseJSON.badRequest("Missing data from client");

		HttpSession session = request.getSession(false);
		
		if (session != null) {
			UserDetailsCustom user = userService.findUserByUsername((String) session.getAttribute("username"));
			
			if (user != null) {
				String key = data.getString("key").trim();
				String value = data.getString("value").trim();
				boolean success = false;
				
				try {
					switch (key) {
						case "email":
							success = updateEmail(value, user);
							break;
						
						case "fullname":
							success = updateFullname(value, user);
							break;
							
						case "phone":
							success = updatePhone(value, user);
							break;
							
						case "address":
							success = updateAddress(value, user);
							break;
					}
					
					if (success)
						return ResponseJSON.ok("Update success");
					
					return ResponseJSON.serverError("Cannot update, server error");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseJSON.serverError("Cannot update: " + e.getMessage());
				}
			}
		}
			
		return ResponseJSON.badRequest("Cannot find user infomation");
	}
	
	@PostMapping(value = "/password/change")
	public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody String json) {
		JSONObject data = new JSONObject(json);
		
		if (!data.has("oldPassword") && !data.has("newPassword"))
			return ResponseJSON.badRequest("Missing data from client");
		
		String oldPass = data.getString("oldPassword").trim();
		String newPass = data.getString("newPassword").trim();
		
		if (oldPass.equals(newPass))
			return ResponseJSON.badRequest("New password is same as old password");
		
		if (newPass.length() < 8)
			return ResponseJSON.badRequest("Required password is more than 8 character");
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			UserDetailsCustom user = userService.findUserByUsername((String) session.getAttribute("username"));
			
			if (user != null) {
				try {
					boolean success = updatePassword(oldPass, newPass, user);
					
					if (success)
						return ResponseJSON.ok("Change password success");
					
					return ResponseJSON.serverError("Cannot change password, server error");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseJSON.serverError("Cannot update: " + e.getMessage());
				}
			}
		}
		
		return ResponseJSON.badRequest("Cannot find user infomation");
	}
	
	private boolean updateEmail(String email, UserDetailsCustom user) {
		String oldEmail = user.getEmail();
		
		if (oldEmail != null && oldEmail.equals(email))
			throw new RuntimeException("Your email is same as current email");
		
		if (userService.findUserByEmail(email) != null)
			throw new RuntimeException("This email was used by another user");
		
		user.setEmail(email);
		user.setModifiedBy(user.getUsername());
		
		return userService.update(user);
	}
	
	 public static boolean isValidPhoneNumber(String phoneNumber) {
	    // Biểu thức chính quy cho số điện thoại 10 chữ số
        String regex = "^[0-9]{10}$"; 

        // Tạo Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);

        // Kiểm tra chuỗi phoneNumber có khớp với Pattern không
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
	
	private boolean updatePhone(String phone, UserDetailsCustom user) {
		String oldPhone = user.getPhone();
		
		if (!isValidPhoneNumber(phone))
			throw new RuntimeException("Your phone is invalid format");
		
		if (oldPhone != null && oldPhone.equals(phone))
			throw new RuntimeException("Your phone is same as current phone");

		user.setPhone(phone);
		user.setModifiedBy(user.getUsername());
		
		return userService.update(user);
	}

	private boolean updateAddress(String address, UserDetailsCustom user) {
		user.setAddress(address);
		user.setModifiedBy(user.getUsername());
		
		return userService.update(user);
	}

	private boolean updatePassword(String oldPass, String newPass, UserDetailsCustom user) {
		if (!passwordEncoder.matches(oldPass, user.getPassword()))
			throw new RuntimeException("Old password is incorrect");
		
		UserDetailsCustom newUser = new UserDetailsCustom(user.getUsername(), passwordEncoder.encode(newPass), user.getAuthorities());
		
		newUser.setId(user.getId());
		newUser.setEmail(user.getEmail());
		newUser.setAddress(user.getAddress());
		newUser.setFullname(user.getFullname());
		newUser.setToken(user.getToken());
		newUser.setPhone(user.getPhone());
		newUser.setModifiedBy(user.getUsername());
		
		return userService.update(newUser);
	}

	private boolean updateFullname(String fullname, UserDetailsCustom user) {
		user.setFullname(fullname);
		user.setModifiedBy(user.getUsername());
		
		return userService.update(user);
	}
}
