package com.Triveous.E_Commerce_API.services;

import com.Triveous.E_Commerce_API.entities.Cart;
import com.Triveous.E_Commerce_API.entities.CartItem;
import com.Triveous.E_Commerce_API.entities.Product;
import com.Triveous.E_Commerce_API.models.CartItemModel;
import com.Triveous.E_Commerce_API.models.CartModel;
import com.Triveous.E_Commerce_API.models.CategoryModel;
import com.Triveous.E_Commerce_API.models.ProductModel;
import com.Triveous.E_Commerce_API.repositories.CartItemRepository;
import com.Triveous.E_Commerce_API.repositories.CartRepository;
import com.Triveous.E_Commerce_API.repositories.ProductRepository;
import com.Triveous.E_Commerce_API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    public ResponseEntity<?> saveCartItems(CartModel cartModel) {
        List<CartItem> cartItems = new ArrayList<>();
        Cart cart = new Cart();
        cart.setUser(userRepository.findById(cartModel.getUser().getId()).get());
        cartModel.getItems().forEach(data->{
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(data.getQuantity());
            cartItem.setProduct(productRepository.findById(data.getProduct().getId()).get());
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        });
        cart.setItems(cartItems);
        return new ResponseEntity<>("Saved Successfully.", HttpStatus.OK);
    }

    public List<CartItemModel> getCartItemsByCartId(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCart(cartRepository.findById(cartId).get());
        List<CartItemModel> cartItemModels = new ArrayList<>();
        cartItems.forEach(data->{
            CartItemModel cartItemModel = new CartItemModel();
            cartItemModel.setId(data.getId());
            cartItemModel.setQuantity(data.getQuantity());
            cartItemModel.setProduct(convertProductEntityInToModel(data.getProduct()));
            cartItemModel.setCart(convertCartEntityInToModel(data.getCart()));
            cartItemModels.add(cartItemModel);
        });
        return cartItemModels;
    }

    private CartModel convertCartEntityInToModel(Cart cart) {
        CartModel cartModel = new CartModel();
        cartModel.setId(cart.getId());
        return cartModel;
    }

    public ProductModel convertProductEntityInToModel(Product product){
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setAvailability(product.getAvailability());
        productModel.setTitle(product.getTitle());
        productModel.setDescription(product.getDescription());
        productModel.setPrice(product.getPrice());
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(product.getCategory().getId());
        categoryModel.setName(product.getCategory().getName());
        productModel.setCategory(categoryModel);
        return productModel;
    }

    public ResponseEntity<?> updateQuantityByCartItemId(Integer quantity, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return new ResponseEntity<>("Saved Successfully.", HttpStatus.OK);
    }

    public ResponseEntity<?> removeItemsFromCartByCartItemId(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItemRepository.delete(cartItem);
        return new ResponseEntity<>("Saved successfully.", HttpStatus.OK);
    }
}
