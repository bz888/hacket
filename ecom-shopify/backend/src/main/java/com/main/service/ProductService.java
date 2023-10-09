package com.main.service;

import com.main.models.Product;
import com.main.models.User;
import com.main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private Boolean validateUser(User user) {
        return User.UserType.ADMIN.equals(user.getRole());
    }

    // Get product by id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // ADMIN
    // Create
    public Product save(Product product, User user) {
        if (this.validateUser(user)) {
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Only admins can save products.");
        }
    }

    // Update (patch)
    public Product updateProduct(Long id, Product productData, User user) {
        if (this.validateUser(user)) {
            productData.setId(id);
            return productRepository.save(productData);
        } else {
            throw new RuntimeException("Only admins can update products.");
        }
    }

    // Delete
    public void deleteProduct(Long id, User user) {
        if (this.validateUser(user)) {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
            } else {
                throw new RuntimeException("Product not found for id " + id);
            }
        } else {
            throw new RuntimeException("Only admins can delete products.");
        }
    }
}