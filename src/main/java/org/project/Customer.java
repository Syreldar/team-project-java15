package org.project;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Storable {
    private String firstName;
    private String lastName;
    private double balance;
    private List<Product> wishList;

    public Customer(String firstName, String lastName, double balance) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("firstName and lastName arguments cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.wishList = new ArrayList<>();
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

    public Product buyProduct(Database database, String shopName, String productName, Integer quantity) {
        return buyProduct(database.findShopByName(shopName), productName, quantity);
    }
    public Product buyProduct(Shop shop, String productName, Integer quantity, Coupon coupon) {
        if (shop == null) {
            throw new IllegalArgumentException("The Shop cannot be null");
        }
        if (productName == null) {
            throw new IllegalArgumentException("The name of the Product cannot be null");
        }

        Product product = shop.findProductByName(productName);
        if (product == null) {
            System.out.printf("%s: Shop found, but Product not found.%n", this.getFullName());
            return null;
        }

        quantity = (quantity == null || quantity <= 0) ? 1 : quantity;

        int productQuantity = product.getCurrentQuantity();
        if (productQuantity < quantity) {
            System.out.printf("%s: The shop doesn't have enough units of %s.%n- Requested: %d;%n- Available: %d;%n",
                    getFullName(), productName, quantity, productQuantity);
            return null;
        }
        product.applyDiscount(coupon);

        double totalPrice = product.getPrice() * quantity;
        if (balance < totalPrice) {
            System.out.printf("%s: I don't have enough money.%n- Remaining: %.2f$;%n- Needed: %.2f$;%n",
                    getFullName(), balance, totalPrice);
            return null;
        }

        subtractFromBalance(totalPrice);
        shop.sellProduct(this, product, quantity);
        return product;
    }

    public Product buyProduct(Shop shop, String productName, Integer quantity) {
        if (shop == null) {
            throw new IllegalArgumentException("The Shop cannot be null");
        }
        if (productName == null) {
            throw new IllegalArgumentException("The name of the Product cannot be null");
        }

        Product product = shop.findProductByName(productName);
        if (product == null) {
            System.out.printf("%s: Shop found, but Product not found.%n", this.getFullName());
            return null;
        }

        quantity = (quantity == null || quantity <= 0) ? 1 : quantity;

        int productQuantity = product.getCurrentQuantity();
        if (productQuantity < quantity) {
            System.out.printf("%s: The shop doesn't have enough units of %s.%n- Requested: %d;%n- Available: %d;%n",
                    getFullName(), productName, quantity, productQuantity);
            return null;
        }

        double totalPrice = product.getPrice() * quantity;
        if (balance < totalPrice) {
            System.out.printf("%s: I don't have enough money.%n- Remaining: %.2f$;%n- Needed: %.2f$;%n",
                    getFullName(), balance, totalPrice);
            return null;
        }

        subtractFromBalance(totalPrice);
        shop.sellProduct(this, product, quantity);
        return product;
    }

    public Product buyProduct(Database database, String shopName, String productName) {
        return buyProduct(database, shopName, productName, null);
    }

    public Product buyProduct(Shop shop, String productName) {
        return buyProduct(shop, productName, null);
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

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void addToBalance(double balance) {
        this.balance += balance;
    }

    public boolean subtractFromBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (this.balance >= balance) {
            this.balance -= balance;
            return true;
        }
        return false;
    }

    public void reviewShop(Database database, Shop shop, float rating, String comment) {
        ShopReview review = new ShopReview(shop, this, rating, comment);
        database.registerShopReview(review);
        shop.addReview(review);
    }

    public void reviewProduct(Database database, Product product, float rating, String comment) {
        ProductReview review = new ProductReview(product, this, rating, comment);
        database.registerProductReview(review);
        product.addReview(review);
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void setWishList(List<Product> wishList) {
        this.wishList = wishList;
    }

    @Override
    public void register(Database database, Chart chart) {
        database.registerCustomer(this);
        chart.addCustomer(this);
    }

    @Override
    public String toString() {
        return String.format("Customer [FirstName: %s, LastName: %s, Balance: %.2f]",
                this.firstName, this.lastName, this.balance);
    }
}
