package org.project.entities.cart;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.project.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Transactional
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart() {
        cartRepository.deleteAll();
    }

    public void removeProductFromCart(Long id, Product product) {
        cartRepository.deleteById(id);
    }

    @Transactional
    public Cart update(Long id, CartDTO cartDTO) throws EntityNotFoundException  {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (cartDTO == null) {
            throw new IllegalArgumentException("cartDTO cannot be null");
        }

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException ("Cart not found with ID: " + id));

        if (cartDTO.getItems() != null) {

            cart.setItems(cartDTO.getItems());
        }

        try {
            return cartRepository.save(cart);
        } catch (DataAccessException e) {
            throw new ServiceException("Error updating cart", e);
        }
    }

    @Transactional
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

    @Transactional
    public void delete(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        try {
            cartRepository.delete(cart);
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting customer", e);
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