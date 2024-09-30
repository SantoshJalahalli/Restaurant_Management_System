package com.hotel.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kitchen")
public class Kitchen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String items;    // List of items, comma-separated (e.g., "Pizza, Pasta, Salad")
    private String quantity; // List of quantities, comma-separated (e.g., "2, 1, 3")
    private int tableNumber;

    // Getters and Setters
}
