package org.project;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Chart chart = new Chart();
        EntityFactory factory = new EntityFactory(database, chart);

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

        factory.createCustomer(new Customer(database, "Enrico", "Drago", 24.52));
        factory.createCustomer(new Customer(database, "Enrico", "Drago", 12.71));

        System.out.println("\nWelcome to the Shopping App!");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String[] options = {
                        "Create a customer",
                        "List available customers",
                        "List available shops",
                        "Make a purchase",
                        "Leave a review",
                        "Show reviews for a shop",
                        "Show charts",
                        "Exit"
                };

                System.out.println("\nOptions:");
                for (int i = 0; i < options.length; i++) {
                    System.out.printf(String.format("%d. %s%n", i + 1, options[i]));
                }
                System.out.print("Select an option: ");

                int option = Integer.parseInt(scanner.nextLine());
                switch (option)
                {
                    case 1 -> Utilities.createCustomer(factory, database, scanner);
                    case 2 -> Utilities.listCustomers(database);
                    case 3 -> Utilities.listShops(database);
                    case 4 -> Utilities.makePurchase(database, scanner);
                    case 5 -> Utilities.leaveReview(database, scanner);
                    case 6 -> Utilities.showReviews(database, scanner);
                    case 7 -> Utilities.showCharts(database, scanner);
                    case 8 -> {
                        System.out.println(Utilities.GOODBYE_MESSAGE);
                        System.exit(0); // Terminate the program
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
