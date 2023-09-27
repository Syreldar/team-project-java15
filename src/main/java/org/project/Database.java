package org.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Database {
    private final List<Product> products;
    private final List<Shop> shops;
    private final List<Customer> customers;
    private final List<Review> reviews;
    private final Map<Product, Integer> productReviewCount = new HashMap<>();
    private final Map<Shop, Integer> shopReviewCount = new HashMap<>();

    public Database() {
        this.products = new ArrayList<>();
        this.shops = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }

        products.add(product);
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("The shop cannot be null");
        }

        this.shops.add(shop);
        System.out.printf("Database: Shop %s by %s added!%n", shop.getName(), shop.getOwnerName());
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("The customer cannot be null");
        }

        this.customers.add(customer);
        System.out.printf("Database: Customer %s added!%n", customer.getFullName());
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Map<Product, Integer> getProductReviewCount() {
        return productReviewCount;
    }

    public Map<Shop, Integer> getShopReviewCount() {
        return shopReviewCount;
    }

    public void addReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("The review cannot be null");
        }

        this.reviews.add(review);
        System.out.printf("Chart: Review (%s, %d) added!%n", review.getComment(), review.getRating());
    }

    public List<Review> getReviewsForShop(Shop shop) {
        List<Review> shopReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review instanceof ShopReview shopReview) {
                if (shopReview.getReviewedShop().equals(shop)) {
                    shopReviews.add(shopReview);
                }
            }
        }
        return shopReviews;
    }

    public Product findProductByName(String productName) {
        return this.products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public Shop findShopByName(String shopName) {
        return this.shops.stream()
                .filter(shop -> shop.getName().equals(shopName))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> findCustomersByName(String firstName, String lastName) {
        List<Customer> matchingCustomers = new ArrayList<>();
        for (Customer customer : this.customers) {
            if (customer.getFirstName().equalsIgnoreCase(firstName) &&
                    customer.getLastName().equalsIgnoreCase(lastName)) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }

    public void registerReview(Product product) {
        productReviewCount.put(product, product.getReviewCount());
    }

    public void registerReview(Shop shop) {
        shopReviewCount.put(shop, shop.getReviewCount());
    }

    public Product getMostReviewedProduct() {
        return productReviewCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Shop getMostReviewedShop() {
        return shopReviewCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
