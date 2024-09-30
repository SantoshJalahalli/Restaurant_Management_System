package com.hotel.hotel;

import com.hotel.hotel.entity.Kitchen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/kitchen")
    public List<Kitchen> getKitchenOrders() {
        return orderService.getAllKitchenOrders();
    }
}
