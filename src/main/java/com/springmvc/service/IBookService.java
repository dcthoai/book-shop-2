package com.springmvc.service;

import java.util.List;

import com.springmvc.dto.BookDTO;
import com.springmvc.dto.request.SearchModel;
import com.springmvc.model.Book;
import com.springmvc.model.Category;
import com.springmvc.model.Language;

public interface IBookService {
	public int insertBook(Book book);
	public boolean updateBook(Book book);
	public boolean deleteBook(int id);
	public Book getBookById(int id);
	
	public String getBookCategory(int categoryId);
	public String getBookLanguage(int languageId);
	
	public List<Book> getNewestBooks();
	public List<Book> getLatestReleaseBooks();
	public List<Book> filterBooks(SearchModel searchModel);
	public List<Book> searchBook(String keyword);
	
	public List<BookDTO> sortByNewest(List<BookDTO> listBooks);
	public List<BookDTO> sortByLowPrice(List<BookDTO> listBooks);
	public List<BookDTO> sortByHighPrice(List<BookDTO> listBooks);
	public List<BookDTO> sortByNameAZ(List<BookDTO> listBooks);
	public List<BookDTO> sortByDiscount(List<BookDTO> listBooks);
	public List<BookDTO> sortByReleaseDate(List<BookDTO> listBooks);
	public List<BookDTO> sortByPurchases(List<BookDTO> listBooks);
	
	public List<Category> getAllCategories();
	public List<Language> getAllLanguage();
	
	public List<Book> suggestBooks();
	
	public int insertCategory(Category category);
	public boolean updateCategory(Category category);
	public boolean deleteCategory(int categoryId);
	public Category getCategoryById(int id);
	
	public int insertLanguage(Language language);
	public boolean updateLanguage(Language language);
	public boolean deleteLanguage(int languageId);
	public Language getLanguageById(int id);
}