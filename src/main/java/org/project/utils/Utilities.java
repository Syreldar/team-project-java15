package org.project.utils;

import java.util.Scanner;

public class Utilities
{
    /*
    private static Customer getSpecificCustomer(Scanner scanner, List<Customer> matchingCustomers) {
        if (matchingCustomers.size() == 1) {
            return matchingCustomers.get(0);
        }

        while (true) {
            System.out.println("Multiple customers with the same name and surname found:");
            printMatchingCustomersData(matchingCustomers);
            System.out.print("Select a customer: ");

            int selectedCustomerIndex = Integer.parseInt(scanner.nextLine());
            if (selectedCustomerIndex >= 1 && selectedCustomerIndex <= matchingCustomers.size()) {
                return matchingCustomers.get(selectedCustomerIndex - 1);
            }

            System.out.println("Invalid selection. Please try again.");
        }
    }

    public static void makePurchase(Database database, Scanner scanner) {
        System.out.print("Enter customer's full name: ");
        String customerFullName = scanner.nextLine();

        List<Customer> matchingCustomers = database.findCustomersByName(customerFullName);
        if (matchingCustomers.isEmpty()) {
            System.out.println("Customer not found.");
            return;
        }

        Customer purchasingCustomer = getSpecificCustomer(scanner, matchingCustomers);
        if (purchasingCustomer == null) {
            System.out.println("Something went wrong (purchasingCustomer == null).");
            return;
        }

        System.out.print("Enter shop name: ");
        String shopName = scanner.nextLine();

        Shop purchaseShop = database.findShopByName(shopName);
        if (purchaseShop == null) {
            System.out.println("Shop not found.");
            return;
        }

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        if (!purchaseShop.containsProduct(productName)) {
            System.out.println("Product not found in the shop.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Product purchasedProduct = purchasingCustomer.buyProduct(purchaseShop, productName, quantity);
        if (purchasedProduct == null) {
            System.out.println("Purchase failed.");
            return;
        }

        System.out.println("Purchase successful!");
        System.out.printf("Product: %s;%n", purchasedProduct.getName());
        System.out.printf("Quantity: %d;%n", quantity);
        System.out.printf("Total Cost: %.2f$;%n", purchasedProduct.getPrice() * quantity);
        System.out.println("------------------------------");
        askContinueOrExitProgram(scanner);
    }

    public static void leaveReview(EntityFactory factory, Scanner scanner) {
        Database database = factory.database();
        if (database == null) {
            System.out.println("Database not found.");
            return;
        }

        System.out.print("Enter customer's full name: ");
        String reviewerFullName = scanner.nextLine();

        List<Customer> matchingReviewers = database.findCustomersByName(reviewerFullName);
        if (matchingReviewers.isEmpty()) {
            System.out.println("Reviewer not found.");
            return;
        }

        Customer reviewer = getSpecificCustomer(scanner, matchingReviewers);
        if (reviewer == null) {
            System.out.println("Something went wrong (reviewer == null).");
            return;
        }

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Review a Shop");
            System.out.println("2. Review a Product");
            System.out.println("3. Back");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 -> shopReview(database, scanner, reviewer);
                case 2 -> productReview(database, scanner, reviewer);
                case 3 -> MainMenu(factory);
                case 4 -> {
                    System.out.println(GOODBYE_MESSAGE);
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    */

    public static void MainMenu() {
        System.out.println("\nWelcome to the Shopping App!");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String[] options = {
                        "Make a purchase",
                        "Exit"
                };

                System.out.println("\nOptions:");
                for (int i = 0; i < options.length; i++) {
                    System.out.printf(String.format("%d. %s%n", i + 1, options[i]));
                }
                System.out.print("Select an option: ");

                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    //case 4 -> Utilities.makePurchase(scanner);
                    default -> {
                        System.exit(0); // Terminate the program
                    }
                }
            }
        }
    }
}
