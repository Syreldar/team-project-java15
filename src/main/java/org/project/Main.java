package org.project;

import java.util.*;

public class Main
{
    public static void main(String[] args) {
        Database database = new Database();
        Chart chart = new Chart();
        EntityFactory factory = new EntityFactory(database, chart);

        List<Shop> shopsList = factory.createShops(Arrays.asList(
                new Shop("ShopA", "Carlo", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 500, 7),
                        new Product(Category.CLEANING, "Detergent", 10,21),
                        new Product(Category.FOOD, "Apple", 2.20,231)
                )),
                new Shop("ShopB", "Para", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 550, 6),
                        new Product(Category.HEALTH, "Medicine", 10, 78),
                        new Product(Category.FOOD, "Banana", 2.10,194)
                )),
                new Shop("ShopC", "Malo", List.of(
                        new Product(Category.CLEANING, "Detergent", 12,24),
                        new Product(Category.HEALTH, "Medicine", 11, 82),
                        new Product(Category.FOOD, "Pear", 2.50,253)
                ))
        ));

        Customer customer = factory.createCustomer(new Customer(database,"Enrico", "Drago", 100));
        Product boughtProduct = customer.buyProduct("ShopA", "Apple");
        // Save to variable in preparation for Review system.

        System.out.println("\nChart by sales:");
        for (Shop shop : chart.getShopsBySells())
            System.out.printf("%s - %d\n", shop.getName(), shop.getTotalSales());

        System.out.println("\nChart by gains:");
        for (Shop shop : chart.getShopsByGains())
            System.out.printf("%s - %.2f\n", shop.getName(), shop.getTotalGains());

        System.out.println("\nChart by Categories:");
        Set<Category> uniqueCategories = new HashSet<>();
        for (Shop shop : shopsList) {
            Category mostSoldCategory = shop.getMostSoldCategory();
            if (mostSoldCategory != null) {
                uniqueCategories.add(mostSoldCategory);
            }
        }

        for (Category category : uniqueCategories) {
            for (Shop shop : chart.getShopsByCategory(category)) {
                System.out.printf("%s - [Sold times: %d, Total gains: %.2f]\n", category.name(), shop.getTotalSales(), shop.getTotalGains());
            }
        }
    }
}
