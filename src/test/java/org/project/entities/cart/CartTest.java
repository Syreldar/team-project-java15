package org.project.entities.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.project.entities.product.Product;
import static org.junit.jupiter.api.Assertions.*;
import static org.project.entities.product.Category.COMPUTER_AND_ACCESSORIES;
import static org.project.entities.product.Category.FOOD_AND_BEVERAGES;


class CartTest {
    @InjectMocks
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testGetTotalProductsInCartWithEmptyCart() {
        int result = cart.getTotalProductsInCart();
        assertEquals(0, result);
    }

    @Test
    void testGetTotalProductsInCartWithNonEmptyCart() {
        Product product1 = new Product(COMPUTER_AND_ACCESSORIES, "Computer", "Razer", 550.00, 2);
        Product product2 = new Product(FOOD_AND_BEVERAGES, "Apple", "Emperor", 2.20, 3);
        cart.addProduct(product1);
        cart.addProduct(product2);

        int result = cart.getTotalProductsInCart();
        assertEquals(5, result);
    }

    @Test
    void testGetTotalProductsInCartAfterRemovingProduct() {
        Product product1 = new Product(COMPUTER_AND_ACCESSORIES, "Computer", "Razer", 550.00, 2);
        Product product2 = new Product(FOOD_AND_BEVERAGES, "Apple", "Emperor", 2.20, 3);
        cart.addProduct(product1);
        cart.addProduct(product2);

        cart.removeProduct(product1);

        int result = cart.getTotalProductsInCart();
        assertEquals(3, result);
    }
}