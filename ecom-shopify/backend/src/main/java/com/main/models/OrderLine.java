package com.main.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderLine() {
    }

    public OrderLine(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }

 /* Unused
   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
  */

}
