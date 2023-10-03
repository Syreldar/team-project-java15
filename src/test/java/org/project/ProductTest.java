package org.project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {
    private Product product;

    @Before
    public void setUp() {
        product = new Product(Category.ELECTRONICS, "Laptop", 1000.0, 10);
    }

    @Test
    public void testReduceQuantity() {
        product.reduceQuantity(5);
        assertEquals(5, product.getCurrentQuantity());

        product.reduceQuantity();
        assertEquals(4, product.getCurrentQuantity());

        product.reduceQuantity(10);
        assertEquals(0, product.getCurrentQuantity());
    }

    @Test
    public void testGetSoldAmount() {
        product.reduceQuantity(3);
        assertEquals(3, product.getSoldAmount());
        assertEquals(7, product.getCurrentQuantity());
    }

    @Test
    public void testReduceQuantityWithNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> product.reduceQuantity(null));
    }

    @Test
    public void testReduceQuantityWithZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> product.reduceQuantity(0));
    }

    @Test
    public void testReduceQuantityWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> product.reduceQuantity(-5));
    }

    @Test
    public void testReduceQuantityToZero() {
        product.reduceQuantity(50);
        assertEquals(0, product.getCurrentQuantity());
    }
}
