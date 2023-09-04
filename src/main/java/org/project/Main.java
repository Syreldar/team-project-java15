package org.project;

import java.util.*;

public class Main
{
    public static void main(String[] args) {
        Chart chart = new Chart();

        List<Shop> shopsList = Arrays.asList(
                new Shop("Carlos", "NegozioA", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 10),
                        new Product(Category.CLEANING, "Detergent", 10),
                        new Product(Category.HEALTH, "Medicine", 10),
                        new Product(Category.FOOD, "Apple", 10)
                )),
                new Shop("Carlos", "NegozioB", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 10),
                        new Product(Category.CLEANING, "Detergent", 10),
                        new Product(Category.HEALTH, "Medicine", 10),
                        new Product(Category.FOOD, "Apple", 10)
                )),
                new Shop("Carlos", "NegozioC", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 10),
                        new Product(Category.CLEANING, "Detergent", 10),
                        new Product(Category.HEALTH, "Medicine", 10),
                        new Product(Category.FOOD, "Apple", 10)
                )),
                new Shop("Carlos", "NegozioD", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 10),
                        new Product(Category.CLEANING, "Detergent", 10),
                        new Product(Category.HEALTH, "Medicine", 10),
                        new Product(Category.FOOD, "Apple", 10)
                )),
                new Shop("Carlos", "NegozioE",List.of(
                        new Product(Category.ELECTRONICS, "Computer", 10),
                        new Product(Category.CLEANING, "Detergent", 10),
                        new Product(Category.HEALTH, "Medicine", 10),
                        new Product(Category.FOOD, "Apple", 10)
                )),
                new Shop("Carlos", "NegozioF", List.of(
                        new Product(Category.ELECTRONICS, "Computer", 10),
                        new Product(Category.CLEANING, "Detergent", 10),
                        new Product(Category.HEALTH, "Medicine", 10),
                        new Product(Category.FOOD, "Apple", 10)
                ))
        );

        shopsList.forEach(chart::addShop);

        System.out.println("Classifica per vendite:");
        for (Shop shop : chart.getShopsByGains())
            System.out.printf("%s - %.2f\n", shop.getName(), shop.getTotalGains());

        Set<Category> uniqueCategories = new HashSet<>();
        for (Shop shop : shopsList)
            uniqueCategories.add(shop.getMostSoldCategory());

        for (Category category : uniqueCategories)
        {
            for (Shop shop : chart.getShopsByCategory(category))
                System.out.printf("%s - %.2f\n", shop.getName(), shop.getTotalGains());
        }
    }
}
