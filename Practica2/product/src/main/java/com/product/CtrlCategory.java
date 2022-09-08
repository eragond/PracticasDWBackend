package com.product;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController		

public class CtrlCategory {
	String[] nombres = {"Juan", "Memo", "Hernan", "Ramiro", "Sergio", "Carlos"};
	
	@RequestMapping(value = "/category", produces="application/json")
	public ArrayList<Category> Responsilla() {		
		CategoryList clst = new CategoryList();
		
		for(int i = 0; i < nombres.length; i++) 
			clst.createCategory(new Category(i, nombres[i]));		
	
		return clst.getCatsArrayList();
	}
}
