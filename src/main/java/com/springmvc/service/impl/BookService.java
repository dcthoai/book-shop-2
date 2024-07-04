package com.springmvc.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.BookDAO;
import com.springmvc.dao.impl.CategoryDAO;
import com.springmvc.dao.impl.LanguageDAO;
import com.springmvc.dto.BookDTO;
import com.springmvc.dto.request.BookRequest;
import com.springmvc.dto.request.SearchModel;
import com.springmvc.mapper.MapperBook;
import com.springmvc.model.Book;
import com.springmvc.model.Category;
import com.springmvc.model.Language;
import com.springmvc.service.IBookService;

@Service
public class BookService implements IBookService{
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private LanguageDAO languageDAO;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private MediaFileService mediaFileService;
	
	@Override
	public int insertBook(Book book) {
		int bookId = bookDAO.insert(book);
		return bookId;
	}

	@Override
	public boolean updateBook(Book book) {
		int affectedRows = bookDAO.update(book);
		return affectedRows > 0;
	}

	@Override
	public boolean deleteBook(int id) {
		Book book = getBookById(id);
		
		if (book != null) {
			mediaFileService.deleteFile(mediaService.getMediaById(book.getThumbnailId()).getPath());
			mediaService.deleteMedia(book.getThumbnailId());
		}
		
		int affectedRows = bookDAO.delete(id);
		System.out.println("So dong: " + affectedRows);
		return affectedRows > 0;
	}
	
	@Override
	public Book getBookById(int id) {
		Book book = bookDAO.getById(id);
		return book;
	}
	
	@Override
	public String getBookCategory(int categoryId) {
		Category category = bookDAO.getBookCategory(categoryId);
		
		if (category != null)
			return category.getName();
		return "Không có thông tin";
	}
	
	@Override
	public String getBookLanguage(int languageId) {
		Language language = bookDAO.getBookLanguage(languageId);
		
		if (language != null) 
			return language.getName();
		return "Không có thông tin";
	}

	
	@Override
	public List<Book> getNewestBooks() {
		List<Book> listBooks = bookDAO.getNewestBooks();
		return listBooks;
	}

	@Override
	public List<Book> getLatestReleaseBooks() {
		List<Book> listBooks = bookDAO.getLatestReleaseBooks();
		return listBooks;
	}
	
	@Override
	public List<Book> filterBooks(SearchModel searchModel){
		String sql = "SELECT * FROM `bookstore`.`book` WHERE ";
		
		if (searchModel.getMaxPrice() > 0)
			sql += "price BETWEEN " + searchModel.getMinPrice() + " AND " + searchModel.getMaxPrice();
		else 
			sql += "price > " + searchModel.getMinPrice();
		
		if (searchModel.getCategoryId() > 0)
			sql += " AND categoryId = " + searchModel.getCategoryId();
		
		if (searchModel.getLanguageId() > 0)
			sql += " AND languageId = " + searchModel.getLanguageId();
				
		/*
		 * if (searchModel.getStars() > 0) sql += " AND stars > " +
		 * searchModel.getStars();
		 */
		
		List<Book> listBooks = bookDAO.executeQuery(sql, new MapperBook());
		return listBooks.isEmpty() ? Collections.emptyList() : listBooks;
	}
	
	@Override
	public List<Book> searchBook(String keyword){
		String s = "%" + keyword.trim() + "%";

		List<Book> listBooks = bookDAO.searchBook(s);
		return listBooks;
	}
	
	@Override
	public List<BookDTO> sortByNewest(List<BookDTO> listBooks){
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return book2.getCreatedDate().compareTo(book1.getCreatedDate());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<BookDTO> sortByLowPrice(List<BookDTO> listBooks) {
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return Long.compare(book1.getSellPrice(), book2.getSellPrice());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<BookDTO> sortByHighPrice(List<BookDTO> listBooks) {
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return Long.compare(book2.getSellPrice(), book1.getSellPrice());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<BookDTO> sortByNameAZ(List<BookDTO> listBooks) {
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return book1.getTitle().compareTo(book2.getTitle());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<BookDTO> sortByDiscount(List<BookDTO> listBooks) {
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return Float.compare(book2.getDiscount(), book1.getDiscount());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<BookDTO> sortByReleaseDate(List<BookDTO> listBooks) {
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return book2.getReleaseDate().compareTo(book1.getReleaseDate());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<BookDTO> sortByPurchases(List<BookDTO> listBooks) {
		Comparator<BookDTO> comparator = new Comparator<BookDTO>() {
            @Override
            public int compare(BookDTO book1, BookDTO book2) {
                return Integer.compare(book2.getPurchases(), book1.getPurchases());
            }
        };

        Collections.sort(listBooks, comparator);

        return listBooks;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategories();
	}

	@Override
	public List<Language> getAllLanguage() {
		return languageDAO.getAllLanguages();
	}

	@Override
	public List<Book> suggestBooks() {
		return bookDAO.getRandomBook();
	}
	
	public Book transferRequest(BookRequest bookRequest) {
		Book book = new Book();
		
		if (bookRequest.getId() != null)
			book.setId(bookRequest.getId());
        book.setLanguageId(bookRequest.getLanguageId());
        book.setCategoryId(bookRequest.getCategoryId());
        book.setPages(bookRequest.getPages());
        book.setWeight(bookRequest.getWeight());
        book.setStock(bookRequest.getStock());
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setSize(bookRequest.getSize());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
        book.setPrice(bookRequest.getPrice());
        book.setDiscount(bookRequest.getDiscount());
        book.setReleaseDate(bookRequest.getReleaseDate());
        
        return book;
	}

	@Override
	public int insertCategory(Category category) {
		return categoryDAO.insert(category);
	}

	@Override
	public boolean updateCategory(Category category) {
		int affectedRows = categoryDAO.update(category);
		
		return affectedRows > 0;
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		int affectedRows = categoryDAO.delete(categoryId);
		
		return affectedRows > 0;
	}

	@Override
	public int insertLanguage(Language language) {
		return languageDAO.insert(language);
	}

	@Override
	public boolean updateLanguage(Language language) {
		int affectedRows = languageDAO.update(language);
		
		return affectedRows > 0;
	}

	@Override
	public boolean deleteLanguage(int languageId) {
		int affectedRows = languageDAO.delete(languageId);
		
		return affectedRows > 0;
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryDAO.getById(id);
	}

	@Override
	public Language getLanguageById(int id) {
		return languageDAO.getById(id);
	}
}
