package org.project;

import org.project.database.Database;
import org.project.factory.EntityFactory;
import org.project.models.Category;
import org.project.models.Customer;
import org.project.models.Product;
import org.project.models.Shop;
import org.project.utils.Utilities;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        EntityFactory factory = new EntityFactory(database);

        factory.createShops(List.of(
                new Shop("ShopA", "Carlo", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 500, 7),
                        new Product(Category.CLEANING, "Detergent", 10, 21),
                        new Product(Category.FOOD, "Apple", 2.20, 231)
                )),
                new Shop("ShopB", "Para", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 550, 6),
                        new Product(Category.HEALTH, "Medicine", 10, 78),
                        new Product(Category.FOOD, "Banana", 2.10, 194)
                )),
                new Shop("ShopC", "Malo", List.of(
                        new Product(Category.CLEANING, "Detergent", 12, 24),
                        new Product(Category.HEALTH, "Medicine", 11, 82),
                        new Product(Category.FOOD, "Pear", 2.50, 253)
                ))
        ));

        factory.createCustomer(new Customer("Enrico", "Drago", 24.52));
        factory.createCustomer(new Customer("Enrico", "Drago", 12.71));

        Utilities.MainMenu(factory);
    }
}
