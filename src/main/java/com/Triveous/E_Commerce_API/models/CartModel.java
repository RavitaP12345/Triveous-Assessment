package com.Triveous.E_Commerce_API.models;
import com.Triveous.E_Commerce_API.entities.User;
import java.util.List;

public class CartModel {
    private Long id;
    private User user;
    private List<CartItemModel> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItemModel> getItems() {
        return items;
    }

    public void setItems(List<CartItemModel> items) {
        this.items = items;
    }
}
