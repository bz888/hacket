package com.main.service;

import com.main.models.Order;
import com.main.models.OrderLine;
import com.main.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    // Read - Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Create
    @Transactional
    public Order save(Order order) {
        // https://stackoverflow.com/questions/42256527/how-to-avoid-java-util-concurrentmodificationexception-in-entity-merging-in-jpa
        // ConcurrentModification Exception
        List<OrderLine> orderLines = new ArrayList<>(order.getOrderLines());

        orderLines.forEach(orderLine -> {
            orderLine.setOrder(order);
        });
        return orderRepository.save(order);
    }
    // Read - Get a OrderLine by Order ID
    public List<OrderLine> getOrderLinesByOrderId(Long id) {
        return orderRepository.findById(id)
                .map(Order::getOrderLines)
                .orElseThrow(() -> new RuntimeException("Order not found for id " + id));
    }
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    @Transactional
    public Order updateOrder(Long id, Order orderData) {
        if (orderRepository.existsById(id)) {
            orderData.setId(id);
            List<OrderLine> orderLines = new ArrayList<>(orderData.getOrderLines());

            orderLines.forEach(orderLine -> orderLine.setOrder(orderData));
            return orderRepository.save(orderData);
        } else {
            throw new RuntimeException("Order not found for id " + id);
        }
    }
    @Transactional
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found for id " + id);
        }
    }
}
