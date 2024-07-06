package com.springmvc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.springmvc.dao.IBookDAO;
import com.springmvc.mapper.MapperBook;
import com.springmvc.mapper.MapperCategory;
import com.springmvc.mapper.MapperLanguage;
import com.springmvc.model.Book;
import com.springmvc.model.Category;
import com.springmvc.model.Language;

@Repository 
public class BookDAO extends AbstractDAO<Book> implements IBookDAO{

	@Override
	public int insert(Book book) {
		String sql = "INSERT INTO `book` ("
			    	+ "title, description, size, author, publisher, "
			    	+ "languageid, categoryid, thumbnailid, "
			    	+ "pages, weight, stock, price, "
			    	+ "discount, releasedate, createdby) "
			    	+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		
		int bookId = executeInsert(sql, book.getTitle(),
										book.getDescription(),
										book.getSize(),
										book.getAuthor(),
										book.getPublisher(),
										book.getLanguageId(),
										book.getCategoryId(),
										book.getThumbnailId(),
										book.getPages(),
										book.getWeight(),
										book.getStock(),
										book.getPrice(),
										book.getDiscount(),
										book.getReleaseDate(),
										book.getCreatedBy());
		
		return bookId;
	}

	@Override
	public int update(Book book) {
		String sql = "UPDATE `book` SET "
						+ "title = ?, description = ?, size = ?, author = ?, publisher = ?, "
				    	+ "languageid = ?, categoryid = ?, thumbnailid = ?, "
				    	+ "pages = ?, weight = ?, stock = ?, price = ?, "
				    	+ "discount = ?, releasedate = ?, modifiedBy = ? "
						+ "WHERE `id` = ?";
		
		int affectedRows = executeUpdate(sql, book.getTitle(),
												book.getDescription(),
												book.getSize(),
												book.getAuthor(),
												book.getPublisher(),
												book.getLanguageId(),
												book.getCategoryId(),
												book.getThumbnailId(),
												book.getPages(),
												book.getWeight(),
												book.getStock(),
												book.getPrice(),
												book.getDiscount(),
												book.getReleaseDate(),
												book.getModifiedBy(),
												book.getId());
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `book` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}
	
	@Override
	public Book getById(int id) {
		String sql = "SELECT * FROM `book` WHERE (`id` = ?)";
		
		List<Book> listBooks = executeQuery(sql, new MapperBook(), id);
		return listBooks.isEmpty() ? null : listBooks.get(0);
	}

	@Override
	public List<Book> getNewestBooks() {
		String sql = "SELECT * FROM `book` ORDER BY `createdDate` DESC LIMIT 36";
		
		List<Book> listBooks = executeQuery(sql, new MapperBook());
		return listBooks;
	}
	
	public List<Book> getLatestReleaseBooks(){
		String sql = "SELECT * FROM `book` ORDER BY `releaseDate` DESC LIMIT 36";
		
		List<Book> listBooks = executeQuery(sql, new MapperBook());
		return listBooks;
	}

	@Override
	public Category getBookCategory(int categoryId) {
		String sql = "SELECT * FROM `category` WHERE (`id` = ?)";
		
		List<Category> listCategories = _jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			    public void setValues(PreparedStatement preparedStatement) throws SQLException {
			        preparedStatement.setInt(1, categoryId);
			    }
			    
			}, new MapperCategory());
		
		return listCategories.isEmpty() ? null : listCategories.get(0);
	}

	@Override
	public Language getBookLanguage(int languageId) {
		String sql = "SELECT * FROM `language` WHERE (`id` = ?)";
		
		List<Language> listLanguages = _jdbcTemplate.query(sql, new PreparedStatementSetter() {
			
			    public void setValues(PreparedStatement preparedStatement) throws SQLException {
			        preparedStatement.setInt(1, languageId);
			    }
			    
			}, new MapperLanguage());
		
		return listLanguages.isEmpty() ? null : listLanguages.get(0);
	}
	
	@Override
	public List<Book> searchBook(String keyword){
		String sql = "SELECT * FROM `book` WHERE (`title` LIKE ?)";
		
		List<Book> listBooks = executeQuery(sql, new MapperBook(), keyword);
		
		return listBooks;
	}

	@Override
	public List<Book> getRandomBook() {
		String sql = "SELECT * FROM `book` ORDER BY RAND() LIMIT 6";
		
		List<Book> listBooks = executeQuery(sql, new MapperBook());
		
		return listBooks;
	}
}