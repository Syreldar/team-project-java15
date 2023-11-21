package org.project.entities.order;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.cart.Cart;
import org.project.entities.cart.CartRepository;
import org.project.entities.customer.Customer;
import org.project.entities.customer.CustomerRepository;
import org.project.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Cart cart = cartRepository.findByCustomerId(orderDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for Customer"));

        List<Product> products = cart.getProducts();

        Order order = new Order(customer, products);

        try {
            return orderRepository.save(order);
        } catch (DataAccessException e) {
            throw new ServiceException("Error creating order", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error getting all orders", e);
        }
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        try {
            return orderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Order with id %d not found", id)));
        } catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error finding Order with ID %d", id), e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Order with ID %d not found", id));
        }

        try {
            orderRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting Order with ID %d", id), e);
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            orderRepository.deleteAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting all orders", e);
        }
    }
}