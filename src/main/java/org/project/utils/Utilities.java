package org.project.utils;

import java.util.Scanner;

public class Utilities
{
    public static final String GOODBYE_MESSAGE = "Thank you for using Team 1's Shopping App project! Bye!";

    private static void askContinueOrExitProgram(Scanner scanner)
    {
        System.out.println("Do you want to continue? (y/n) ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("n")) {
            System.out.println(GOODBYE_MESSAGE);
            System.exit(0);
        } else if (!answer.equalsIgnoreCase("y")) {
            System.out.println("Invalid Input. Program Aborted.");
            System.exit(1);
        }
    }

    /*
    private static void printMatchingCustomersData(List<Customer> customers) {
        IntStream.range(0, customers.size())
                .forEach(i -> {
                    Customer currentCustomer = customers.get(i);
                    System.out.printf("%d. %s: %.2f$%n", i + 1, currentCustomer.getFullName(), currentCustomer.getBalance());
                });
    }

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

    public static void showReviews(Database database, Scanner scanner) {
        System.out.print("Enter the name of the shop to list reviews: ");
        String shopName = scanner.nextLine();

        Shop shop = database.findShopByName(shopName);
        if (shop == null) {
            System.out.println("Shop not found.");
            return;
        }

        System.out.printf("Which kind of review do you want to see for %s?%n", shop.getName());
        System.out.println("1. Shop Reviews");
        System.out.println("2. Product Reviews");
        System.out.print("Select an option: ");

        int reviewType = Integer.parseInt(scanner.nextLine());
        switch (reviewType)
        {
            case 1 -> showShopReviews(shop);
            case 2 -> showProductReviews(scanner, shop);
            default -> System.out.println("Invalid option. Please try again.");
        }

        askContinueOrExitProgram(scanner);
    }

    private static void showShopReviews(Shop shop) {
        List<ShopReview> shopReviews = shop.getReviews();
        if (shopReviews.isEmpty()) {
            System.out.printf("No reviews found for %s.%n", shop.getName());
            return;
        }

        System.out.println("Shop Reviews:");
        for (ShopReview review : shopReviews) {
            System.out.printf("Rating: %.1f; Comment: %s; Customer: %s%n",
                    review.getRating(), review.getComment(), review.getReviewer().getFullName());
        }

        double totalRating = 0;
        for (ShopReview review : shopReviews) {
            totalRating += review.getRating();
        }
        double averageRating = totalRating / shopReviews.size();
        System.out.printf("Average Rating for the shop %s: %.2f%n", shop.getName(), averageRating);
    }

    private static void showProductReviews(Scanner scanner, Shop shop) {
        System.out.print("Enter the name of the product to list reviews: ");
        String productName = scanner.nextLine();

        Product product = shop.findProductByName(productName);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        List<ProductReview> productReviews = product.getReviews();
        if (productReviews.isEmpty()) {
            System.out.printf("No reviews found for %s.%n", product.getName());
            return;
        }

        System.out.println("Product Reviews:");
        for (ProductReview review : productReviews) {
            System.out.printf("Rating: %.1f; Comment: %s; Customer: %s%n",
                    review.getRating(), review.getComment(), review.getReviewer().getFullName());
        }

        double totalRating = 0;
        for (ProductReview review : productReviews) {
            totalRating += review.getRating();
        }
        double averageRating = totalRating / productReviews.size();
        System.out.printf("Average Rating for %s's %s: %.2f%n", shop.getName(), product.getName(), averageRating);
    }
    */

    public static void MainMenu() {
        System.out.println("\nWelcome to the Shopping App!");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String[] options = {
                        "List available customers",
                        "List available shops",
                        "Make a purchase",
                        "Leave a review",
                        "Show reviews for a shop",
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
                    //case 5 -> Utilities.leaveReview(scanner);
                    //case 6 -> Utilities.showReviews(scanner);
                    default -> {
                        System.out.println(Utilities.GOODBYE_MESSAGE);
                        System.exit(0); // Terminate the program
                    }
                }
            }
        }
    }
}
