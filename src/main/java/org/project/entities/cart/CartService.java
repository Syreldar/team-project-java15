package org.project.entities.cart;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.product.Product;
import org.project.entities.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;


    @Transactional
    public void add(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cart not found with ID: %d", cartId)));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product not found with ID: %d", productId)));

        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    @Transactional
    public Cart update(Long id, CartDTO cartDTO) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (cartDTO == null) {
            throw new IllegalArgumentException("cartDTO cannot be null");
        }

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cart not found with ID: %d", id)));

        if (cartDTO.getProducts() != null) {
            cart.setProducts(cartDTO.getProducts());
        }

        try {
            return cartRepository.save(cart);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error updating cart", e);
        }
    }

    @Transactional(readOnly = true)
    public Cart findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        try {
            return cartRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("Cart with id %d not found", id)));
        } catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting Cart with ID %d", id));
        }
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        try {
            return cartRepository.findAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Error finding all carts", e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!cartRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Cart with ID %d not found", id));
        }

        try {
            cartRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException(
                    String.format("Error deleting Cart with ID %d", id));
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            cartRepository.deleteAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting all carts", e);
        }
    }
}