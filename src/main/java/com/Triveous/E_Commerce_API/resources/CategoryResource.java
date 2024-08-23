package com.Triveous.E_Commerce_API.resources;

import com.Triveous.E_Commerce_API.models.CategoryModel;
import com.Triveous.E_Commerce_API.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategoryDetails")
    public ResponseEntity<?> saveCategoryDetails(@RequestBody CategoryModel categoryModel){
        return categoryService.saveCategoryDetails(categoryModel);
    }
    @GetMapping("/getCategoryList")
    public List<CategoryModel> getCategoryList(){
        return categoryService.getCategoryList();
    }
}
