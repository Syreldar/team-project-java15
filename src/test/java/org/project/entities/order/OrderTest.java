package org.project.entities.order;

import org.junit.jupiter.api.Test;
import org.project.entities.product.Product;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.project.entities.product.Category.*;

class OrderTest {
    @Test
    public void testCalculateTotalCostProducts() {
        Order order = new Order();

        List<Product> products = new ArrayList<>();
        products.add(new Product(COMPUTER_AND_ACCESSORIES, "Computer", "Razer", 550.00, 5));
        products.add(new Product(FOOD_AND_BEVERAGES, "Apple", "Emperor", 2.20, 3));
        products.add(new Product(HEALTHCARE_PRODUCTS, "Medicine", "DrJohnson", 10.00, 4));

        order.setProducts(products);

        double expectedTotalCost = 10.0 + 20.0 + 30.0;
        double actualTotalCost = order.calculateTotalCost();

        assertEquals(expectedTotalCost, actualTotalCost);
    }
}

