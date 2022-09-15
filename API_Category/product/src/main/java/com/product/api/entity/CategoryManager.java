package com.product.api.entity;

import java.util.Scanner;

public class CategoryManager {
    private int tempId  = 0;
    private Category tempCat;
    private Scanner scan;
    CategoryList clst;

    public CategoryManager() {
        scan = new Scanner(System.in);
        clst = new CategoryList();
    }

    public void testCreateCategory() {
        System.out.print("Introduce una nueva categoria \nId: "); 
        tempId = scan.nextInt();
        System.out.print("Nombre: "); 
        String nom = scan.next();
        tempCat = new Category(tempId, nom);
        clst.createCategory(tempCat);
        System.out.println("Se ha creado o modificado la " + tempCat); 
    }

    public void testGetCategories() {
        System.out.println("Estas son tus categorias: "); 
        String ret = clst.getCategories();
        if(ret == "")
            System.out.println("No existen categorias registradas"); 
        System.out.println(ret); 
    }

    public void testGetCategory() {
        System.out.print("Ingresa el id de la categoria a buscar: "); 
        tempId = scan.nextInt();
        tempCat = clst.getCategory(tempId);
        if(tempCat != null){
            System.out.println(tempCat.toString()); 
        } else {
            System.out.println("No existe una categoria con el id ingresado"); 
        }
    }

    public void testDeleteCategory() {
        System.out.print("Ingresa el id de la categoria a remover: "); 
        tempId = scan.nextInt();
        tempCat = clst.deleteCategory(tempId); 
        if(tempCat != null){
            System.out.println("Se ha eliminado la " + tempCat.toString()); 
        } else {
            System.out.println("No existe una categoria con el id ingresado"); 
        }
    }

    
    public static void main(String[] args) { 
        CategoryManager catman = new CategoryManager();
        Scanner scan = new Scanner(System.in);

        // Categorias de ejemplo
        catman.clst.createCategory(new Category(0, "Juan"));
        catman.clst.createCategory(new Category(1, "Pablo"));
        catman.clst.createCategory(new Category(2, "Mario"));
        catman.clst.createCategory(new Category(3, "Ernesto"));
        catman.clst.createCategory(new Category(4, "Milaneso"));
        catman.clst.createCategory(new Category(5, "Daniel"));

        int chos;
        do{
            System.out.println(String.join("\n"
                        , "Que necesitas?"
                        , "[0] Crear/Modificar una categoria"
                        , "[1] Ver la lista de categorias"
                        , "[2] Obtener una categoria"
                        , "[3] Remover una categoria"
                        , "[4] Salir")); 
            chos = scan.nextInt();
            switch (chos) {
                case 0:
                    catman.testCreateCategory();
                    break;
                case 1:
                    catman.testGetCategories();
                    break;
                case 2:
                    catman.testGetCategory();
                    break;
                case 3:
                    catman.testDeleteCategory();
                    break;
            }
        } while(chos != 4);
        System.out.println("Bye :)"); 
        scan.close();
    }
} 
