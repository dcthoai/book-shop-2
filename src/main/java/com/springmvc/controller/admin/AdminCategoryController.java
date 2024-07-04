package com.springmvc.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Category;
import com.springmvc.model.ResponseJSON;
import com.springmvc.service.impl.BookService;

@Controller
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ModelAndView info(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/category-language");
		mav.addObject("categories", bookService.getAllCategories());
		mav.addObject("languages", bookService.getAllLanguage());
		
		return mav;
	}
	
	@GetMapping(value = "/update")
	public ModelAndView updateView(HttpServletRequest request, @RequestParam("id") Integer id) {
		if (id == null)
			return null;
		
		ModelAndView mav = new ModelAndView("admin/update-category");
		mav.addObject("category", bookService.getCategoryById(id));
		
		return mav;
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addCategory(HttpServletRequest request, @RequestBody String jsonString) throws IOException {
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject(jsonString);
		
		if (!jsonObject.has("category"))
			return ResponseJSON.badRequest("Missing data from client");
		
		Category category = new Category();
		category.setName(jsonObject.getString("category"));
		category.setCreatedBy((String) session.getAttribute("username"));
		
		int categoryId = bookService.insertCategory(category);
		
		if (categoryId > 0)
			return ResponseJSON.ok("Save category success");
		
		return ResponseJSON.serverError("Cannot save category, server error");
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> updateCategory(HttpServletRequest request, @RequestBody String jsonString) throws IOException {
		HttpSession session = request.getSession(false);
		
		JSONObject jsonObject = new JSONObject(jsonString);
		
		if (!jsonObject.has("category") || !jsonObject.has("id"))
			return ResponseJSON.badRequest("Missing data from client");
		
		Category category = bookService.getCategoryById(jsonObject.getInt("id"));
		category.setName(jsonObject.getString("category"));
		category.setModifiedBy((String) session.getAttribute("username"));
		
		boolean isSuccess = bookService.updateCategory(category);
		
		if (isSuccess)
			return ResponseJSON.ok("Save category success");
		
		return ResponseJSON.serverError("Cannot save category, server error");
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<?> deleteCategory(HttpServletRequest request, @RequestParam("id") Integer id) throws IOException {
		if (id == null)
			return ResponseJSON.badRequest("Missing data from client");
		
		boolean success = bookService.deleteCategory(id);
		
		if (success)
			return ResponseJSON.ok("Delete category success");
		return ResponseJSON.serverError("Cannot delete this item.");
	}
}
