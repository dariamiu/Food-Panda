package app.foodpanda.model;

public class FoodDTO {

    private String name;

    private Float price;

    private String description;

    private String category;

    private String restaurant;

    public FoodDTO(String name, Float price, String description, String category, String restaurant) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.restaurant = restaurant;
    }

    public FoodDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
