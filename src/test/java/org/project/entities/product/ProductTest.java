package org.project.entities.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.project.entities.product.Category.COMPUTER_AND_ACCESSORIES;

class ProductTest {

    @Test
    void testSetQuantityWithValidValue() {
        Product product = new Product(COMPUTER_AND_ACCESSORIES, "Computer", "Razer", 550.00, 0);
        int validQuantity = 5;
        product.setQuantity(validQuantity);

        assertEquals(validQuantity, product.getQuantity());
    }

    @Test
    void testSetQuantityWithNegativeValue() {
        Product product = new Product(COMPUTER_AND_ACCESSORIES, "Computer", "Razer", 550.00, 0);
        int negativeQuantity = -3;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> product.setQuantity(negativeQuantity));

        assertEquals("You can't set a negative quantity", exception.getMessage());
        assertEquals(0, product.getQuantity());
    }
}


