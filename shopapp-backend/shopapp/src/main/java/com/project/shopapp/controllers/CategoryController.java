package com.project.shopapp.controllers;

import com.project.shopapp.Service.CategoryService;
import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/categories")
//@Validated

public class CategoryController {
    @Autowired
    private   CategoryService categoryService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    //Hiện tất cả các categories
    @GetMapping("") //http://localhost:8088/api/v1/categories?page=1&limit=10
    public ResponseEntity<?> getAllCategories(
            @RequestParam("page")     int page,
            @RequestParam("limit")    int limit
    ) {
        return ResponseEntity.ok(categoryService.getAllCategories().toString());
//        return ResponseEntity.ok(String.format("getAllCategories, page = %d, limit = %d", page, limit));
    }

    @PostMapping("")
    //Nếu tham số truyền vào là 1 object thì sao ? => Data Transfer Object = Request Object
    public ResponseEntity<?> insertCategory( @Valid @RequestBody  CategoryDTO categoryDTO , BindingResult result ) {
    if(result.hasErrors() ==true ){
        List<String> errorMessage  = result.getFieldErrors().stream()
//                .map( e -> {e.getDefaultMessage()}).toList();
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return   ResponseEntity.badRequest().body(errorMessage);
    }
    Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(category);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id , CategoryDTO categoryDTO) {
       Category category =  categoryService.updateCategory(id,categoryDTO );

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleteCategory with id = "+id);
    }
}
