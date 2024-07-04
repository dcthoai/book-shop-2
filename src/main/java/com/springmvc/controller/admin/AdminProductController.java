package com.springmvc.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.springmvc.dto.BookDTO;
import com.springmvc.dto.request.BookRequest;
import com.springmvc.model.Book;
import com.springmvc.model.Media;
import com.springmvc.model.ResponseJSON;
import com.springmvc.service.impl.BookService;
import com.springmvc.service.impl.MediaFileService;
import com.springmvc.service.impl.MediaService;

@Controller
@RequestMapping(value = "/admin/product")
public class AdminProductController {
	
	@Autowired
	private MediaFileService mediaFileService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	private MediaService mediaService;

	@GetMapping(value = "/add")
	public ModelAndView addBook(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/add-book");
		mav.addObject("categories", bookService.getAllCategories());
		mav.addObject("languages", bookService.getAllLanguage());
		
		return mav;
	}
	
	@GetMapping(value = "/edit")
	public ModelAndView editBook(@RequestParam("id") Integer bookId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/edit-book");
		
		if (bookId != null && bookId > 0) {
			Book book = bookService.getBookById(bookId);
			mav.addObject("book", book);
			mav.addObject("mediaService", mediaService);
			mav.addObject("bookService", bookService);
			mav.addObject("categories", bookService.getAllCategories());
			mav.addObject("languages", bookService.getAllLanguage());
		}
		
		return mav;
	}
	
	@PostMapping(value = "/insert")
	public ResponseEntity<?> addNewBook(HttpServletRequest request, @RequestParam("title") String title,
										@ModelAttribute BookRequest bookRequest) throws IOException {
		HttpSession session = request.getSession();
		
		if (mediaFileService.isUploadFile(bookRequest.getThumbnail())) {
			Media media = new Media();
			String imageUrl = mediaFileService.saveFile(bookRequest.getThumbnail());
		
			if (imageUrl != null) {
				media.setPath(imageUrl);
				int thumbnailId = mediaService.insertMedia(media);

				Book book = bookService.transferRequest(bookRequest);
				book.setThumbnailId(thumbnailId);
				book.setCreatedBy((String) session.getAttribute("username"));
				
				int bookId = bookService.insertBook(book);
				
				if (bookId > 0)
					return ResponseJSON.ok("Save book success");
				return ResponseJSON.badRequest("Missing data feild for this book");
			}
				
			return ResponseJSON.serverError("Cannot save image");
		}
		
		return ResponseJSON.badRequest("Missing thumbnail");
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> updateBook(HttpServletRequest request, 
										@ModelAttribute BookRequest bookRequest) throws IOException {
		HttpSession session = request.getSession();
		Book book = bookService.getBookById(bookRequest.getId());

		if (book == null)
			return ResponseJSON.badRequest("Data invalid");

		book.setModifiedBy((String) session.getAttribute("username"));
		
		if (mediaFileService.isUploadFile(bookRequest.getThumbnail())) {
			Media media = new Media();
			String imageUrl = mediaFileService.saveFile(bookRequest.getThumbnail());
		
			if (imageUrl != null) {
				media.setPath(imageUrl);
				int thumbnailId = mediaService.insertMedia(media);
				
				if (thumbnailId > 0) {					
					mediaFileService.deleteFile(mediaService.getMediaById(book.getThumbnailId()).getPath());
					mediaService.deleteMedia(book.getThumbnailId());
					book.setThumbnailId(thumbnailId);
				}
			}
		}
		
		if (bookRequest.getTitle() != null) 
		    book.setTitle(bookRequest.getTitle());
		
		if (bookRequest.getDescription() != null) 
		    book.setDescription(bookRequest.getDescription());
		
		if (bookRequest.getSize() != null) 
		    book.setSize(bookRequest.getSize());
		
		if (bookRequest.getAuthor() != null) 
		    book.setAuthor(bookRequest.getAuthor());
		
		if (bookRequest.getPublisher() != null) 
		    book.setPublisher(bookRequest.getPublisher());
		
		if (bookRequest.getLanguageId() != null) 
		    book.setLanguageId(bookRequest.getLanguageId());
		
		if (bookRequest.getCategoryId() != null) 
		    book.setCategoryId(bookRequest.getCategoryId());
				  
		if (bookRequest.getPages() != null) 
		    book.setPages(bookRequest.getPages());
		
		if (bookRequest.getWeight() != null) 
		    book.setWeight(bookRequest.getWeight());
		
		if (bookRequest.getStock() != null) 
		    book.setStock(bookRequest.getStock());
		
		if (bookRequest.getPrice() != null) 
		    book.setPrice(bookRequest.getPrice());
		
		if (bookRequest.getDiscount() != null) 
		    book.setDiscount(bookRequest.getDiscount());
		
		if (bookRequest.getReleaseDate() != null) 
		    book.setReleaseDate(bookRequest.getReleaseDate());

		boolean isSuccess = bookService.updateBook(book);

		if (isSuccess)
			return ResponseJSON.ok("Success");
		
		return ResponseJSON.serverError("Server error, cannot save this product");
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<?> deleteBook(HttpServletRequest request, @RequestParam("id") Integer bookId) {
		if (bookId != null && bookId > 0) {
			boolean success = bookService.deleteBook(bookId);
				
			if (success)
				return ResponseJSON.ok("Delete book success");
			return ResponseJSON.serverError("Cannot delete this item");
		}
		
		return ResponseJSON.badRequest("Missing id book");
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> searchProduct(HttpServletRequest request, @RequestParam("name") String keyword){
		
		List<Book> listBooks = bookService.searchBook(keyword);
		List<BookDTO> listBookDTOs = new ArrayList<BookDTO>();
		
		for (Book book : listBooks) {
			BookDTO bookDto = new BookDTO();
			
			bookDto.setId(book.getId());
			bookDto.setTitle(book.getTitle());
			bookDto.setAuthor(book.getAuthor());
			bookDto.setCost(book.getPrice());
			bookDto.setDiscount(book.getDiscount());
			bookDto.setSellPrice(book.getSellPrice());
			bookDto.setCreatedDate(book.getCreatedDate());
			bookDto.setStock(book.getStock());
			
			listBookDTOs.add(bookDto);
		}

		Gson gson = new Gson();
		String json = gson.toJson(listBookDTOs);
		
		// Use UTF-8 to transmit data
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}
