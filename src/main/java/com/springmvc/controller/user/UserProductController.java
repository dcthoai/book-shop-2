package com.springmvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Book;
import com.springmvc.service.impl.BookService;
import com.springmvc.service.impl.MediaService;

@Controller
public class UserProductController {
	
	@Autowired
	private BookService bookService;
	@Autowired MediaService mediaService;
	
	@GetMapping("/product")
    public ModelAndView getProduct(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("user/product");
        Book book = bookService.getBookById(id);
        mav.addObject("book", book);
        mav.addObject("language", bookService.getBookLanguage(book.getLanguageId()));
        mav.addObject("category", bookService.getBookCategory(book.getCategoryId()));
        mav.addObject("mediaService", mediaService);
        mav.addObject("bookSuggesstion", bookService.suggestBooks());
        
        return mav;
    }
}
