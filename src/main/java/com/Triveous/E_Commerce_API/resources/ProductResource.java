package com.Triveous.E_Commerce_API.resources;

import com.Triveous.E_Commerce_API.models.ProductModel;
import com.Triveous.E_Commerce_API.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @GetMapping("/getProductByProductId/{productId}")
    public ProductModel getProductByProductId(@PathVariable Long productId){
        return productService.getProductByProductId(productId);
    }

}
