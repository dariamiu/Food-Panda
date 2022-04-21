package app.foodpanda.model;

public class FoodDTO2 {
    private String name;

    private Float price;

    public FoodDTO2(String name, Float price, String description, String category, String restaurant) {
        this.name = name;
        this.price = price;
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
}


