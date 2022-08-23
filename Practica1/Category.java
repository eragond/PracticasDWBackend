
public class Category {

    public int categoryId;
    public String category;

    public Category(int id, String catName) {
        this.categoryId = id;
        this.category = catName;
    }

    public String toString() {
        return "Categoria con id: " + categoryId + " y nombre: " + category;
    }
}
