package com.project.shopapp.Service;

import com.project.shopapp.Repository.CategoryRepository;
import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category.builder().
        name(categoryDTO.getName()).
                build();

        return categoryRepository.save(newCategory);
    }


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->new RuntimeException("category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());

        existingCategory.setName( categoryDTO.getName());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(long id) {
//        Category existingCategory = getCategoryById(id);
         categoryRepository.deleteById(id);
    }
}
