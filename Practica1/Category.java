
public class Category {

    public int category_id;
    public String category;

    public Category(int id, String catName) {
        this.category_id = id;
        this.category = catName;
    }

    public String toString() {
        return "Categoria con id: " + category_id + ", " + category;
    }
}
