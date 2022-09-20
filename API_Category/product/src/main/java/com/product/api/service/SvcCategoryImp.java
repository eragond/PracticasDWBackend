package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;

@Service
public class SvcCategoryImp implements SvcCategory {

	@Autowired
	RepoCategory repo;
	
	@Override
	public List<Category> listCategories() {
		return repo.findByStatus(1);
	}

	@Override
	public Category readCategory(Integer category_id) {
		return repo.findByCategoryId(category_id);
	}

	@Override
	public String createCategory(Category category) {
		Category catSave = (Category) repo.findByCategory(category.getCategory());
		if(catSave == null) {
			repo.createCategory(category.getCategory());
			return "Category created";
		}
		if(catSave.getStatus() == 0) {
			repo.activateCategory(catSave.getCategory_id());
			return "Category has been activated";
		} 
		return "Category already exist";
	}

	@Override
	public String updateCategory(Integer category_id, Category category) {
		Category catSave = (Category) repo.findByCategoryId(category_id);
		if(catSave == null)
			return "Category does not exist";	
		if(catSave.getStatus() == 0) 
			return "Category is not active";
		catSave = (Category) repo.findByCategory(category.getCategory());
		if(catSave != null)
			return "Category already exist";
		repo.updateCategory(category_id, category.getCategory());
		return "Category updated";
	}

	@Override
	public String deleteCategory(Integer category_id) {
		Category catSave = (Category) repo.findByCategoryId(category_id);
		if(catSave == null)
			return "Category does not exist";	
		repo.deleteByCategoryId(category_id);
		return "Category removed";
	}

}
