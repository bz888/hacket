package com.main.models;

import jakarta.persistence.*;

import java.math.BigDecimal; // Despite Double being more efficient, BigDecimal is more accurate and should be used for any monetary calculations

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    public Product() {}

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    // Customer
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    // Admin // Should implement Auth protocols on this
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
