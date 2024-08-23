package com.Triveous.E_Commerce_API.services;

import com.Triveous.E_Commerce_API.entities.*;
import com.Triveous.E_Commerce_API.models.OrderItemModel;
import com.Triveous.E_Commerce_API.models.OrderModel;
import com.Triveous.E_Commerce_API.models.UserModel;
import com.Triveous.E_Commerce_API.repositories.OrderItemRepository;
import com.Triveous.E_Commerce_API.repositories.OrderRepository;
import com.Triveous.E_Commerce_API.repositories.ProductRepository;
import com.Triveous.E_Commerce_API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;
    public ResponseEntity<?> placeOrder(OrderModel orderModel) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        orderModel.getItems().forEach(data->{
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(data.getQuantity());
            Product product = productRepository.findById(data.getProduct().getId()).get();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        });
        order.setItems(orderItems);
        order.setUser(userRepository.findById(orderModel.getUser().getId()).get());
        order.setTotalAmount(orderModel.getTotalAmount());
        order.setOrderDate(LocalDate.now().atStartOfDay());
        orderRepository.save(order);
        return new ResponseEntity<>("Saved Successfully.", HttpStatus.OK);
    }


    public List<OrderModel> getOrderHistory() {
        List<Order> orders = orderRepository.findAll();
        List<OrderModel> orderModels = new ArrayList<>();
        orders.forEach(data->{
            List<OrderItemModel> orderItems = new ArrayList<>();
            data.getItems().forEach(data1->{
                OrderItemModel orderItemModel = new OrderItemModel();
                orderItemModel.setId(data1.getId());
                orderItemModel.setProduct(cartService.convertProductEntityInToModel(data1.getProduct()));
                orderItemModel.setQuantity(data1.getQuantity());
                orderItems.add(orderItemModel);
            });
            OrderModel orderModel = new OrderModel();
            orderModel.setOrderDate(data.getOrderDate());
            orderModel.setUser(convertUserEntityInToUserModel(data.getUser()));
            orderModel.setTotalAmount(data.getTotalAmount());
            orderModel.setItems(orderItems);
            orderModels.add(orderModel);
        });
        return orderModels;
    }

    public UserModel convertUserEntityInToUserModel(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setUsername(user.getUsername());
        return userModel;
    }

    public OrderModel getOrderDetailsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        OrderModel orderModel = new OrderModel();
        List<OrderItemModel> orderItems = new ArrayList<>();
        order.getItems().forEach(data1->{
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setId(data1.getId());
            orderItemModel.setProduct(cartService.convertProductEntityInToModel(data1.getProduct()));
            orderItemModel.setQuantity(data1.getQuantity());
            orderItems.add(orderItemModel);
        });
        orderModel.setOrderDate(order.getOrderDate());
        orderModel.setUser(convertUserEntityInToUserModel(order.getUser()));
        orderModel.setTotalAmount(order.getTotalAmount());
        orderModel.setItems(orderItems);
        return orderModel;
    }
}
