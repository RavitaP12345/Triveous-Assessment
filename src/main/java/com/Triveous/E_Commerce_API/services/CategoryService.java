package com.Triveous.E_Commerce_API.services;

import com.Triveous.E_Commerce_API.entities.Category;
import com.Triveous.E_Commerce_API.entities.Product;
import com.Triveous.E_Commerce_API.models.CategoryModel;
import com.Triveous.E_Commerce_API.models.ProductModel;
import com.Triveous.E_Commerce_API.repositories.CategoryRepository;
import com.Triveous.E_Commerce_API.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> saveCategoryDetails(CategoryModel categoryModel) {
        Category category = new Category();
        category.setName(categoryModel.getName());
        List<Product> products = new ArrayList<>();
        categoryModel.getProducts().forEach(data->{
            Product product = new Product();
            product.setTitle(data.getTitle());
            product.setAvailability(data.getAvailability());
            product.setCategory(category);
            product.setDescription(data.getDescription());
            product.setPrice(data.getPrice());
            products.add(product);
        });
        category.setProducts(products);
        categoryRepository.save(category);
        return new ResponseEntity<>("Saved Successfully.", HttpStatus.OK);
    }

    public List<CategoryModel> getCategoryList() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryModel> categoryModels = new ArrayList<>();
        categories.forEach(data->{
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(data.getId());
            categoryModel.setName(data.getName());
            categoryModels.add(categoryModel);
        });
        return categoryModels;
    }
}
