package com.main.controller;

import com.main.models.Product;
import com.main.models.User;
import com.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // This is a placeholder. In a real-world application, you'd retrieve the authenticated user's details.
    private User getAuthenticatedUser() {
        // For demonstration, returning a dummy admin user.
        // In a real-world scenario, you'd extract this from the authentication context.
        return new User("admin", "123", User.UserType.ADMIN);
    }
    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());

    }

    // Create a product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        User user = getAuthenticatedUser();
        try {
            return ResponseEntity.ok(productService.save(product, user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        User user = getAuthenticatedUser();
        try {
            return ResponseEntity.ok(productService.updateProduct(id, product, user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        User user = getAuthenticatedUser();
        try {
            productService.deleteProduct(id, user);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
