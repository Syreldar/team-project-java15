package org.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Database {
    private List<Product> products;
    private List<Shop> shops;
    private List<Customer> customers;
    private List<Review> reviews;
    private Map<Product, Integer> productReviewCount = new HashMap<>();
    private Map<Shop, Integer> shopReviewCount = new HashMap<>();

    public Database() {
        this.products = new ArrayList<>();
        this.shops = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        shops.add(shop);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        customers.add(customer);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        reviews.add(review);
    }

    public Map<Product, Integer> getProductReviewCount() {
        return productReviewCount;
    }

    public void setProductReviewCount(Map<Product, Integer> productReviewCount) {
        this.productReviewCount = productReviewCount;
    }

    public Map<Shop, Integer> getShopReviewCount() {
        return shopReviewCount;
    }

    public void setShopReviewCount(Map<Shop, Integer> shopReviewCount) {
        this.shopReviewCount = shopReviewCount;
    }

    public Product findProductByName(String name) {
        return products.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public Shop findShopByName(String name) {
        return shops.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public void registerReview(Product product) {
        productReviewCount.put(product, product.getReviewCount());
    }

    public void registerReview(Shop shop) {
        shopReviewCount.put(shop, shop.getReviewCount());
    }

    public Product getMostReviewedProduct() {
        return productReviewCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Shop getMostReviewedShop() {
        return shopReviewCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Review> getReviewsForShop(String shopName) {
        return reviews.stream()
                .filter(review -> review instanceof ShopReview)
                .map(review -> (ShopReview) review)
                .filter(shopReview -> shopReview.getReviewedShop().getName().equalsIgnoreCase(shopName))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsForShop(Shop shop) {
        return reviews.stream()
                .filter(review -> review instanceof ShopReview)
                .map(review -> (ShopReview) review)
                .filter(shopReview -> shopReview.getReviewedShop().equals(shop))
                .collect(Collectors.toList());
    }

    public List<Customer> findCustomersByName(String name) {
        return customers.stream()
                .filter(customer -> customer.getFullName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Database [product: %s, shops: %s, customers: %s, reviews: %s]",
                products, shops, customers, reviews);
    }
    public List<Product> getAllAvailableProducts() {
        List<Product> allAvailableProducts = new ArrayList<>();
        for (Shop shop : shops) {
            allAvailableProducts.addAll(shop.getProducts());
        }
        return allAvailableProducts;
    }
}
