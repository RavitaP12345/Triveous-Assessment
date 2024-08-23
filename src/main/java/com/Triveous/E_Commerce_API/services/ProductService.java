package com.Triveous.E_Commerce_API.services;

import com.Triveous.E_Commerce_API.entities.Category;
import com.Triveous.E_Commerce_API.entities.Product;
import com.Triveous.E_Commerce_API.models.CategoryModel;
import com.Triveous.E_Commerce_API.models.ProductModel;
import com.Triveous.E_Commerce_API.repositories.CategoryRepository;
import com.Triveous.E_Commerce_API.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductModel getProductByProductId(Long productId) {
        ProductModel productModel = new ProductModel();
        try{
            Product product = productRepository.findById(productId).get();
            productModel.setId(product.getId());
            productModel.setPrice(product.getPrice());
            productModel.setAvailability(product.getAvailability());
            Category category = categoryRepository.findById(product.getCategory().getId()).get();
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(category.getId());
            categoryModel.setName(category.getName());
            productModel.setCategory(categoryModel);
        }catch (Exception e){
            e.printStackTrace();;
        }
        return productModel;

    }
}
