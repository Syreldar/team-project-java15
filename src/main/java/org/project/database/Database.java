package org.project.database;

import org.project.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    private List<Product> products;
    private List<Shop> shops;
    private List<Customer> customers;
    private List<ShopReview> shopReviews;
    private List<ProductReview> productReviews;

    public Database() {
        this.products = new ArrayList<>();
        this.shops = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.shopReviews = new ArrayList<>();
        this.productReviews = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<ShopReview> getShopReviews() {
        return shopReviews;
    }

    public void setShopReviews(List<ShopReview> shopReviews) {
        this.shopReviews = shopReviews;
    }

    public List<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReview> productReviews){
        this.productReviews = productReviews;
    }

    public void registerProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public void registerShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be null");
        }
        shops.add(shop);
    }

    public void registerCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        customers.add(customer);
    }

    public void registerShopReview(ShopReview review) {
        if (review == null) {
            throw new IllegalArgumentException("Shop Review cannot be null");
        }
        shopReviews.add(review);
    }

    public void registerProductReview(ProductReview review) {
        if (review == null) {
            throw new IllegalArgumentException("Product Review cannot be null");
        }
        productReviews.add(review);
    }

    public Product findProductByName(String name) {
        return products.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public Shop findShopByName(String name) {
        return shops.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Product> getAllAvailableProducts() {
        return shops.stream()
                .flatMap(shop -> shop.getProducts().stream())
                .collect(Collectors.toList());
    }

    public long getProductReviewCount(Product product) {
        return productReviews.stream().filter(r -> r.getReviewedProduct().equals(product)).count();
    }

    public long getShopReviewCount(Shop shop) {
        return shopReviews.stream().filter(r -> r.getReviewedShop().equals(shop)).count();
    }

    public List<ShopReview> getShopReviews(String shopName) {
        return shopReviews.stream()
                .filter(r -> r.getReviewedShop().getName().equalsIgnoreCase(shopName))
                .collect(Collectors.toList());
    }

    public List<ProductReview> getProductReviews(String productName) {
        return productReviews.stream()
                .filter(r -> r.getReviewedProduct().getName().equalsIgnoreCase(productName))
                .collect(Collectors.toList());
    }

    public List<Customer> findCustomersByName(String name) {
        return customers.stream()
                .filter(c -> c.getFullName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Database [product: %s, shops: %s, customers: %s, shopReviews: %s, productReviews: %s]",
                products, shops, customers, shopReviews, productReviews);
    }
}
