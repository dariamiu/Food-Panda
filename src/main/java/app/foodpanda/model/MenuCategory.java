package app.foodpanda.model;
import app.foodpanda.dto.FoodDTO;
import java.util.List;

public class MenuCategory {

    private String categoryName;

    private List<FoodDTO> foods;

    public MenuCategory(String categoryName, List<FoodDTO> foods) {
        this.categoryName = categoryName;
        this.foods = foods;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }
}
