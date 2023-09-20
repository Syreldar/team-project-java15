package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Storable {
    private String firstName;
    private String lastName;
    private BigDecimal balance;
    private final Database database;
    private List<Product> wishList;

    public Customer(Database database, String firstName, String lastName, double balance) {
        if (database == null || firstName == null || lastName == null) {
            throw new IllegalArgumentException("The database, firstName and lastName arguments cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("The initial balance cannot be negative");
        }

        this.database = database;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = BigDecimal.valueOf(balance);
        this.wishList= new ArrayList<>();
    }
    public void addToWishList(Shop shop, String productName) {
        Product product = shop.findProductByName(productName);
        if (product != null && !wishList.contains(product)) {
            wishList.add(product);
        }
    }
    public void removeFromWishList(Product product) {
        if (product != null) {
            wishList.remove(product);
        }
    }
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The amount to withdraw must be positive");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The amount to deposit must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public Product buyProduct(String shopName, String productName, Integer quantity) {
        if (shopName == null) {
            throw new IllegalArgumentException("The name of the Shop cannot be null");
        }
        if (productName == null) {
            throw new IllegalArgumentException("The name of the Product cannot be null");
        }

        Shop shop = this.database.findShopByName(shopName);
        if (shop == null) {
            System.out.printf("%s: Shop not found.%n", this.getFullName());
            return null;
        }

        Product product = shop.findProductByName(productName);
        if (product == null) {
            System.out.printf("%s: Shop found, but Product not found.%n", this.getFullName());
            return null;
        }

        quantity = (quantity == null || quantity <= 0) ? 1 : quantity;

        int productQuantity = product.getQuantity();
        if (productQuantity < quantity) {
            System.out.printf("%s: The shop doesn't have enough units of %s.%n- Requested: %d;%n- Available: %d;%n", this.getFullName(), productName, quantity, productQuantity);
            return null;
        }

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        if (this.balance.compareTo(totalPrice) < 0) {
            System.out.printf("%s: I don't have enough money.%n- Remaining: %.2f;%n- Needed: %.2f;%n", this.firstName, this.balance, totalPrice);
            return null;
        }

        this.withdraw(totalPrice);
        shop.sellProduct(this, product, quantity);
        return product;
    }

    public Product buyProduct(String shopName, String productName) {
        return buyProduct(shopName, productName, null);
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
    public void register(Database database, Chart chart) {
        database.addCustomer(this);
        chart.addCustomer(this);
    }
    
    @Override
    public String toString() {
        return String.format("Customer [FullName: %s, Balance: %.2f]",
                this.getFullName(), this.balance);
    }
}
