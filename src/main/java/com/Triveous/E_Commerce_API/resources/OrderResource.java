package com.Triveous.E_Commerce_API.resources;

import com.Triveous.E_Commerce_API.models.OrderModel;
import com.Triveous.E_Commerce_API.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderModel orderModel){
        return orderService.placeOrder(orderModel);
    }
    @GetMapping("/getOrderHistory")
    public List<OrderModel> getOrderHistory(){
        return orderService.getOrderHistory();
    }
    @GetMapping("/getOrderDetailsByOrderId/{orderId}")
    public OrderModel getOrderDetailsByOrderId(@PathVariable Long orderId){
        return orderService.getOrderDetailsByOrderId(orderId);
    }
}
