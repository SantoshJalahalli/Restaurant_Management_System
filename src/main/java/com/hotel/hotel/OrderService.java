package com.hotel.hotel;

import com.hotel.hotel.entity.Kitchen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Order placeOrder(Order order) {
        // Save order to the database
        Order savedOrder = orderRepository.save(order);

        // Create a new Kitchen entry
        Kitchen kitchen = new Kitchen();
        kitchen.setItems(order.getItems());
        kitchen.setQuantity(order.getQuantity());
        kitchen.setTableNumber(order.getTableNumber());

        // Save the kitchen order
        kitchenRepository.save(kitchen);

        return savedOrder;
    }

    public List<Kitchen> getAllKitchenOrders() {
        return kitchenRepository.findAll();
    }
}
