package com.product.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;
import com.product.api.entity.CategoryList;

@RestController		
@RequestMapping("/category")
public class CtrlCategory {
	String[] nombres = {"Abarrotes", "Electronica"};
	CategoryList clst;
	
	public CtrlCategory() {
		clst = new CategoryList();
		for(int i = 0; i < nombres.length; i++) 
			clst.createCategory(new Category(i, nombres[i]));		
	}
	
	@GetMapping
	public ResponseEntity<ArrayList<Category>> listCategories() {		
		return new ResponseEntity<>(clst.getCatsArrayList(), HttpStatus.OK);
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<Category> readCategory(@PathVariable Integer category_id){
		Category cat = clst.getCategory(category_id);							   
		if (cat == null)
			return new ResponseEntity<>(cat, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(cat, HttpStatus.OK);
		
	} 
	
	@PostMapping
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			String msg = "";
			msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		Category cat = clst.getCategory(category.getCategory()); 
		System.out.println(category.getCategory());
		if(cat == null) { //TEMPORAL EN LO QUE SE USA LA DB
			clst.createCategory(new Category(clst.size()+1, category.getCategory()));
			return new ResponseEntity<>("category has been created", HttpStatus.CREATED);
		}
		if(cat.getStatus() == 0) {
			cat.setStatus(1);
			return new ResponseEntity<>("category has been activated", HttpStatus.OK);
		}	
		return new ResponseEntity<>("category already exist", HttpStatus.BAD_REQUEST);
		
		
	}
	
	@PutMapping("/{category_id}")
	public ResponseEntity<String> updateCategory(@PathVariable Integer category_id, @Valid @RequestBody Category category, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			String msg = "";
			msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		Category cat = clst.getCategory(category_id); 
		if(cat == null)
			return new ResponseEntity<>("category does not exist", HttpStatus.NOT_FOUND);
		if(cat.getStatus() == 0)
			return new ResponseEntity<>("category is not active", HttpStatus.BAD_REQUEST);
		
		cat.setCategory(category.getCategory());
		return new ResponseEntity<>("category updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer category_id) {
		Category cat = clst.deleteCategory(category_id);
		if(cat == null)
			return new ResponseEntity<>("category does not exist", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>("category removed", HttpStatus.OK);
	}
}
