package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.ILanguageDAO;
import com.springmvc.mapper.MapperLanguage;
import com.springmvc.model.Language;

@Repository
public class LanguageDAO extends AbstractDAO<Language> implements ILanguageDAO{

	@Override
	public int insert(Language language) {
		String sql = "INSERT INTO `bookstore`.`language` (`name`, `code`, `createdBy`) VALUES (?, ?, ?)";
		
		int categoryId = executeInsert(sql, language.getName(), language.getCode(), language.getCreatedBy());
		return categoryId;
	}

	@Override
	public int update(Language language) {
		String sql = "UPDATE `bookstore`.`language` SET `name` = ?, `code` = ?, `modifiedBy` = ? WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, language.getName(), language.getCode(), language.getModifiedBy(), language.getId());
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `bookstore`.`language` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}
	
	@Override
	public Language getById(int id) {
		String sql = "SELECT * FROM `bookstore`.`language` WHERE (`id` = ?)";
		List<Language> listLanguages = executeQuery(sql, new MapperLanguage(), id);
		
		return listLanguages.isEmpty() ? null : listLanguages.get(0);
	}

	@Override
	public List<Language> getAllLanguages() {
		String sql = "SELECT * FROM `bookstore`.`language`";
		List<Language> listLanguages = executeQuery(sql, new MapperLanguage());
		
		return listLanguages;
	}
}
