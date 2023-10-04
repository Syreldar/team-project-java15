package org.project;

import java.util.List;
import java.util.Scanner;

public class Utilities
{
    public static final String GOODBYE_MESSAGE = "Thank you for using the Shopping App! Bye!";

    private static void askContinueOrExitProgram() {
        System.out.println("Do you want to continue? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("n")) {
            System.out.println(GOODBYE_MESSAGE);
            System.exit(0);
        }
    }

    public static void createCustomer(EntityFactory factory, Database database, Scanner scanner) {
        System.out.print("Enter customer's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter customer's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter customer's initial balance: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        Customer customer = factory.createCustomer(new Customer(database, firstName, lastName, initialBalance));
        System.out.printf("Customer created: %s, Initial balance: %.2f$.%n", customer.getFullName(), initialBalance);
        askContinueOrExitProgram();
    }

    private static void printMatchingCustomersData(List<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            Customer currentCustomer = customers.get(i);
            System.out.printf("%d. %s: %.2f$%n", i + 1, currentCustomer.getFullName(), currentCustomer.getBalance());
        }
    }

    public static void listCustomers(Database database) {
        List<Customer> customers = database.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("Available Customers:");
        printMatchingCustomersData(customers);
        askContinueOrExitProgram();
    }

    public static void listShops(Database database) {
        List<Shop> shops = database.getShops();
        if (shops.isEmpty()) {
            System.out.println("No shops available.");
            return;
        }

        System.out.println("Available Shops:");
        shops.forEach(shop -> System.out.println(shop.getName()));
        askContinueOrExitProgram();
    }

    private static Customer getSpecificCustomer(List<Customer> matchingCustomers, Scanner scanner) {
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

        Customer purchasingCustomer = getSpecificCustomer(matchingCustomers, scanner);
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
        askContinueOrExitProgram();
    }

    private static void shopReview(Customer reviewer, Database database, Scanner scanner) {
        System.out.print("Enter the name of the shop to review: ");
        String shopName = scanner.nextLine();

        Shop shop = database.findShopByName(shopName);
        if (shop == null) {
            System.out.println("Shop not found.");
            return;
        }

        System.out.print("Enter rating (1-5): (one floating point allowed, e.g. 3.8)");
        float rating = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter review comment (optional): ");
        String comment = scanner.nextLine();

        reviewer.reviewShop(shop, rating, comment);
        System.out.println("Shop review submitted successfully.");
        askContinueOrExitProgram();
    }

    private static void productReview(Customer reviewer, Database database, Scanner scanner) {
        System.out.print("Enter the name of the product to review: ");
        String productName = scanner.nextLine();

        Product product = database.findProductByName(productName);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter review comment (optional): ");
        String comment = scanner.nextLine();

        reviewer.reviewProduct(product, rating, comment);
        System.out.println("Product review submitted successfully.");
        askContinueOrExitProgram();
    }

    public static void leaveReview(Database database, Scanner scanner) {
        System.out.print("Enter customer's full name: ");
        String reviewerFullName = scanner.nextLine();

        List<Customer> matchingReviewers = database.findCustomersByName(reviewerFullName);
        if (matchingReviewers.isEmpty()) {
            System.out.println("Reviewer not found.");
            return;
        }

        Customer reviewer = getSpecificCustomer(matchingReviewers, scanner);
        if (reviewer == null) {
            System.out.println("Something went wrong (reviewer == null).");
            return;
        }

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Review a Shop");
            System.out.println("2. Review a Product");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option)
            {
                case 1 -> shopReview(reviewer, database, scanner);
                case 2 -> productReview(reviewer, database, scanner);
                case 3 -> {
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

        List<Review> shopReviews = database.getReviewsForShop(shop);
        if (shopReviews.isEmpty()) {
            System.out.println("No reviews found for this shop.");
            return;
        }

        System.out.printf("Reviews for %s's %s:%n", shop.getOwnerName(), shop.getName());
        for (Review review : shopReviews) {
            System.out.printf("Rating: %.2f out of 5;%n", review.getRating());
            System.out.printf("Comment: %s;%n", review.getComment());
            System.out.printf("Customer: %s;%n", review.getReviewer().getFullName());
            System.out.println("------------------------------");
        }
        askContinueOrExitProgram();
    }

    public static void showCharts(Database database, Scanner scanner) {
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
        switch (chartOption)
        {
            case 1 -> System.out.println("Not yet implemented 1.");
            case 2 -> System.out.println("Not yet implemented 2.");
            case 3 -> {}
            case 4 -> {
                System.out.println(GOODBYE_MESSAGE);
                System.exit(0); // Terminate the program
            }
            default -> System.out.println("Invalid chart option.");
        }
        askContinueOrExitProgram();
    }
}
