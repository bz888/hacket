package com.main.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    // I don't know if this is necessary, this can be executed on the client side ?
//    public Double getTotalOrderPrice() {
//        return orderLines.stream()
//                .mapToDouble(line -> line.getProduct().getPrice() * line.getQuantity())
//                .sum();
//    }

    public Order() {
    }

    public Order(User user, List<OrderLine> orderLines) {
        this.user = user;
        this.orderLines = orderLines;
    }

    // Repository takes care of this ?
//    public Long getId() {
//        return id;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

//    public void setOrderLines(List<OrderLine> orderLines) {
//        this.orderLines = orderLines;
//    }
}
