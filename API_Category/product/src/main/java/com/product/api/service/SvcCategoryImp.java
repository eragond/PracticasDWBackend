package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

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
		Category catSave = repo.findByCategoryId(category_id);
		if(catSave == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "category does not exist");
		return catSave;
	}

	@Override
	public ApiResponse createCategory(Category category) {
		Category catSave = (Category) repo.findByCategory(category.getCategory());
		if(catSave == null) {
			repo.createCategory(category.getCategory());
			return new ApiResponse("category created");
		}
		if(catSave.getStatus() == 0) {
			repo.activateCategory(catSave.getCategory_id());
			return new ApiResponse("category has been activated");
		} 
		throw new ApiException(HttpStatus.BAD_REQUEST, "category already exist");
	}

	@Override
	public ApiResponse updateCategory(Integer category_id, Category category) {
		Category catSave = (Category) repo.findByCategoryId(category_id);
		if(catSave == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "category does not exist");
		if(catSave.getStatus() == 0) 
			throw new ApiException(HttpStatus.BAD_REQUEST, "category is not active");
		catSave = (Category) repo.findByCategory(category.getCategory());
		if(catSave != null)
			throw new ApiException(HttpStatus.BAD_REQUEST, "category already exist");
		repo.updateCategory(category_id, category.getCategory());
		return new ApiResponse("category updated");
	}

	@Override
	public ApiResponse deleteCategory(Integer category_id) {
		Category catSave = (Category) repo.findByCategoryId(category_id);
		if(catSave == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "category does not exist");
		repo.deleteByCategoryId(category_id);
		return new ApiResponse("category removed");
	}

}
