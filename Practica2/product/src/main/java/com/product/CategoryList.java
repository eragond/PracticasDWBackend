package com.product;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryList {
    private HashMap<Integer, Category> catList;

    public CategoryList() {
        this.catList = new HashMap<Integer, Category>();
    }

    public void createCategory(Category cat) {
        this.catList.put(cat.categoryId, cat);
    }

    public String getCategories() {
        String c = "";
        for(Category cat: this.catList.values())
            c += cat + "\n";
        return c;
    }

    public Category getCategory(int catid) {
        return this.catList.get(catid);
    }

    public Category deleteCategory(int catid) {
        return this.catList.remove(catid);
    }
    
    // Regresa los values del hashmap en un ArrayList
    public ArrayList<Category> getCatsArrayList() {
    	return new ArrayList<Category>(this.catList.values());
    }
}
