package com.main.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class User {

    public enum UserType {
        CUSTOMER, ADMIN
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password; // can use encoding in the future
    @Enumerated(EnumType.STRING)
    private UserType role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // one user can have many orders
    private Set<Order> orders;

    public User() {}

    public User(String username, String password, UserType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getRole() {
        return role;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
