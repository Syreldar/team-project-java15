package org.project.utils;

import org.project.database.Database;
import org.project.factory.EntityFactory;
import org.project.models.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Utilities
{
    public static final String GOODBYE_MESSAGE = "Thank you for using the Shopping App! Bye!";

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

    public static void createCustomer(EntityFactory factory, Scanner scanner)
    {
        System.out.print("Enter customer's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter customer's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter customer's initial balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        Customer customer = factory.createCustomer(new Customer(firstName, lastName, initialBalance));
        System.out.printf("Customer created: %s, Initial balance: %.2f$.%n", customer.getFullName(), initialBalance);
        askContinueOrExitProgram(scanner);
    }

    private static void printMatchingCustomersData(List<Customer> customers) {
        IntStream.range(0, customers.size())
                .forEach(i -> {
                    Customer currentCustomer = customers.get(i);
                    System.out.printf("%d. %s: %.2f$%n", i + 1, currentCustomer.getFullName(), currentCustomer.getBalance());
                });
    }

    public static void listCustomers(Database database, Scanner scanner) {
        List<Customer> customers = database.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("Available Customers:");
        printMatchingCustomersData(customers);
        askContinueOrExitProgram(scanner);
    }

    public static void listShops(Database database, Scanner scanner) {
        List<Shop> shops = database.getShops();
        if (shops.isEmpty()) {
            System.out.println("No shops available.");
            return;
        }

        System.out.println("Available Shops:");
        shops.forEach(shop -> System.out.println(shop.getName()));
        askContinueOrExitProgram(scanner);
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

    private static void shopReview(Database database, Scanner scanner, Customer reviewer) {
        System.out.print("Enter the name of the shop to review: ");
        String shopName = scanner.nextLine();

        Shop shop = database.findShopByName(shopName);
        if (shop == null) {
            System.out.println("Shop not found.");
            return;
        }

        System.out.print("Enter rating (1-5) [one floating point allowed, e.g. 3.8]: ");
        float rating = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter review comment (optional): ");
        String comment = scanner.nextLine();

        reviewer.reviewShop(database, shop, rating, comment);
        System.out.println("Shop review submitted successfully.");
        askContinueOrExitProgram(scanner);
    }

    private static void productReview(Database database, Scanner scanner, Customer reviewer) {
        System.out.print("Enter the name of the product's shop: ");
        String shopName = scanner.nextLine();

        Shop shop = database.findShopByName(shopName);
        if (shop == null) {
            System.out.println("Shop not found.");
            return;
        }

        System.out.print("Enter the name of the product to review: ");
        String productName = scanner.nextLine();

        Product product = shop.findProductByName(productName);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter rating (1-5): ");
        float rating = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter review comment (optional): ");
        String comment = scanner.nextLine();

        reviewer.reviewProduct(database, product, rating, comment);
        System.out.println("Product review submitted successfully.");
        askContinueOrExitProgram(scanner);
    }

    public static void leaveReview(EntityFactory factory, Database database, Scanner scanner) {
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
                case 3 -> MainMenu(factory, database);
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

    public static void showCharts(EntityFactory factory, Database database, Scanner scanner) {
        System.out.println("Select a type of chart to display:");
        String[] options = {
                "Not Implemented 1",
                "Not Implemented 2",
                "Go back",
                "Exit"
        };

        System.out.println("\nOptions:");
        for (int i = 0; i < options.length; i++) {
            System.out.printf(String.format("%d. %s%n", i + 1, options[i]));
        }

        System.out.print("Select an option: ");

        int chartOption = Integer.parseInt(scanner.nextLine());
        switch (chartOption) {
            case 1 -> System.out.println("Not yet implemented 1.");
            case 2 -> System.out.println("Not yet implemented 2.");
            case 3 -> MainMenu(factory, database);
            case 4 -> {
                System.out.println(GOODBYE_MESSAGE);
                System.exit(0); // Terminate the program
            }
            default -> System.out.println("Invalid chart option.");
        }
        askContinueOrExitProgram(scanner);
    }

    public static void MainMenu(EntityFactory factory, Database database) {
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
                switch (option) {
                    case 1 -> Utilities.createCustomer(factory, scanner);
                    case 2 -> Utilities.listCustomers(database, scanner);
                    case 3 -> Utilities.listShops(database, scanner);
                    case 4 -> Utilities.makePurchase(database, scanner);
                    case 5 -> Utilities.leaveReview(factory, database, scanner);
                    case 6 -> Utilities.showReviews(database, scanner);
                    case 7 -> Utilities.showCharts(factory, database, scanner);
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
