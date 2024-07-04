package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Category;

public interface ICategoryDAO extends IGenericDAO<Category>{
	public List<Category> getAllCategories();
}