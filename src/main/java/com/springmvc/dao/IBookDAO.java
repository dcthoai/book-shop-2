package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Book;
import com.springmvc.model.Category;
import com.springmvc.model.Language;

public interface IBookDAO extends IGenericDAO<Book> {
	public List<Book> getNewestBooks();
	public List<Book> getLatestReleaseBooks();
	
	public Category getBookCategory(int categoryId);
	public Language getBookLanguage(int languageId);
	
	public List<Book> searchBook(String keyword);
	public List<Book> getRandomBook();
}