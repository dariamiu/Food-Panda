package app.foodpanda.service;

import app.foodpanda.model.Category;
import app.foodpanda.model.CategoryDTO;
import app.foodpanda.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDTO modelToDTO(Category category){
        return new CategoryDTO(category.getName());
    }

    public List<CategoryDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        List <CategoryDTO> categoryDTOS = categories.stream().map(this::modelToDTO).collect(Collectors.toList());
        for (CategoryDTO categoryDTO : categoryDTOS) {
            System.out.println(categoryDTO.getName());
        }
        return  categoryDTOS;
    }
}
