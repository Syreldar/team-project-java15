import org.junit.Before;
import org.junit.Test;
import org.project.models.Category;
import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.Shop;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class ShopTest {
    private Shop shop;

    @Before
    public void setUp() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(Category.CLOTHING, "T-Shirt", 20.0, 50));
        products.add(new Product(Category.ELECTRONICS, "Smartphone", 500.0, 30));
        shop = new Shop("Fashion Store", "Alice");
    }

    @Test
    public void testContainsProduct() {
        assertTrue(shop.containsProduct("T-Shirt"));
        assertFalse(shop.containsProduct("Shoes"));
    }

    @Test
    public void testSellProduct() {
        Customer customer = new Customer("John", "Doe", 100.0);
        Product tShirt = shop.findProductByName("T-Shirt");
        assertTrue(shop.sellProduct(customer, tShirt, 3));
        assertEquals(47, tShirt.getCurrentQuantity());

        Product smartphone = shop.findProductByName("Smartphone");
        assertThrows(IllegalArgumentException.class, () -> shop.sellProduct(customer, smartphone, 40));
        assertEquals(30, smartphone.getCurrentQuantity());
    }
}