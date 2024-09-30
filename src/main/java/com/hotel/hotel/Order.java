package com.hotel.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")  // Renaming to avoid SQL reserved keyword conflicts
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String items;    // List of items, comma-separated (e.g., "Pizza, Pasta, Salad")
    private String price;    // List of prices, comma-separated (e.g., "10, 15, 5")
    private String quantity; // List of quantities, comma-separated (e.g., "2, 1, 3")
    private int tableNumber;

    // Getters and Setters
}
