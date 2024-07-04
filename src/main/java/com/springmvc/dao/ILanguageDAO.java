package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Language;

public interface ILanguageDAO extends IGenericDAO<Language>{
	public List<Language> getAllLanguages();
}
