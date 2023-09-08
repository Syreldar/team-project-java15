package org.project;

import java.math.BigDecimal;

public class Customer {
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private final Database database;

    public Customer(Database database, String firstName, String lastName, double balance) {
        this.database = database;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = BigDecimal.valueOf(balance);
    }

    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void buyProduct(String shopName, String productName, Integer quantity) {
        Shop shop = this.database.findShopByName(shopName);
        if (shop == null) {
            System.out.printf("%s: Shop not found.%n", this.getFullName());
            return;
        }

        Product product = shop.findProductByName(productName);
        if (product == null) {
            System.out.printf("%s: Shop found, but Product not found.%n", this.getFullName());
            return;
        }

        quantity = (quantity == null || quantity <= 0) ? 1 : quantity;

        int productQuantity = product.getQuantity();
        if (productQuantity < quantity) {
            System.out.printf("%s: The shop doesn't have enough units of %s.%n- Requested: %d;%n- Available: %d;%n", this.getFullName(), productName, quantity, productQuantity);
            return;
        }

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        if (this.balance.compareTo(totalPrice) < 0) {
            System.out.printf("%s: I don't have enough money.%n- Remaining: %.2f;%n- Needed: %.2f;%n", this.firstName, this.balance, totalPrice);
            return;
        }

        this.withdraw(totalPrice);
        shop.sellProduct(this, product, quantity);
    }

    public void buyProduct(String shopName, String productName) {
        buyProduct(shopName, productName, null);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Customer [FullName: %s, Balance: %.2f]",
                this.getFullName(), this.balance);
    }
}
