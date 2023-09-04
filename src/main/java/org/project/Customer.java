package org.project;

public class Customer {
    private String firstName;
    private String lastName;
    private double balance;

    public Customer(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void buyProduct(Shop shop, Product product) {
        double price = product.getPrice();
        if (this.balance < price) {
            System.out.printf("Insufficient balance to purchase. Current balance: %.2f%n", this.balance);
            return;
        }

        if (!shop.containsProduct(product)) {
            System.out.println("The product is not available in the shop.");
            return;
        }

        this.balance -= price;
        shop.sellProduct(product, price);
        System.out.printf("Purchase of %s from shop %s successful. Current balance: %.2f%n",
                product.getName(), shop.getName(), this.balance);
    }

    @Override
    public String toString() {
        return  String.format("Cliente %s %s %f", this.firstName, this.lastName, this.balance);
    }
}
