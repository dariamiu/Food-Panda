package app.foodpanda.repository;

import app.foodpanda.model.Category;
import app.foodpanda.model.Restaurant;

import java.util.List;

public interface CategoryRepository extends AbstractRepository<Category> {

    Category findByName(String category);

}
