package app.foodpanda.service;

import app.foodpanda.model.Category;
import app.foodpanda.dto.CategoryDTO;
import app.foodpanda.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 Service class containing the methods with the application logic that uses data from the category table
 @author Daria Miu
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    /**
     * Method that transforms an object Category into an object CategoryDTO that only contains Category information
     * relevant for the frontend
     * @param category object of type Category
     * @return object of type CategoryDTO
     */
    public CategoryDTO modelToDTO(Category category){
        if (category.getName() == null){
            logger.error("Category can not be converted to DTO because the name is null");
        }
        return new CategoryDTO(category.getName());
    }

    /**
     * Method to find all the Categories in the database
     * @return a list of CategoryDTOs to be sent as a response to the frontend
     */
    public List<CategoryDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            logger.warn("There are no categories in the database");
        }
        return categories.stream().map(this::modelToDTO).collect(Collectors.toList());
    }
}
