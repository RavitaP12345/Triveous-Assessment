package com.Triveous.E_Commerce_API.resources;

import com.Triveous.E_Commerce_API.models.CartItemModel;
import com.Triveous.E_Commerce_API.models.CartModel;
import com.Triveous.E_Commerce_API.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartResource {
    @Autowired
    private CartService cartService;

    @PostMapping("/saveCartItems")
    public ResponseEntity<?> saveCartItems(@RequestBody CartModel cartModel){
        return cartService.saveCartItems(cartModel);
    }
    @GetMapping("/getCartItemsByCartId/{cartId}")
    public List<CartItemModel> getCartItemsByCartId(@PathVariable Long cartId){
        return cartService.getCartItemsByCartId(cartId);
    }
    @GetMapping("/updateQuantityByCartItemId/{quantity}{cartItemId}")
    public ResponseEntity<?> updateQuantityByCartItemId(@PathVariable Integer quantity, @PathVariable Long cartItemId){
        return cartService.updateQuantityByCartItemId(quantity, cartItemId);
    }
    @DeleteMapping("/removeItemsFromCartByCartItemId/{cartItemId}")
    public ResponseEntity<?> removeItemsFromCartByCartItemId(@PathVariable Long cartItemId){
        return cartService.removeItemsFromCartByCartItemId(cartItemId);
    }
}
