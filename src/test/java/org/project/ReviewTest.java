package org.project;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;

public class ReviewTest {
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("John", "Doe", 100.0);
    }

    @Test
    public void testShopReview() {
        Shop shop = new Shop("MyShop", "Owner", new ArrayList<>());
        ShopReview shopReview = new ShopReview(shop, customer, 4.5f, "Great shop!");
        assertEquals(customer, shopReview.getReviewer());
        assertEquals(4.5f, shopReview.getRating(), 0.01);
        assertEquals("Great shop!", shopReview.getComment());
    }

    @Test
    public void testProductReview() {
        Product tShirt = new Product(Category.CLOTHING, "T-Shirt", 15.0, 50);
        ProductReview productReview = new ProductReview(tShirt, customer, 5.0f, "Excellent quality!");
        assertEquals(customer, productReview.getReviewer());
        assertEquals(5.0f, productReview.getRating(), 0.01);
        assertEquals("Excellent quality!", productReview.getComment());
    }
}
