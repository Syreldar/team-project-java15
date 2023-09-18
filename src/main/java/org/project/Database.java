package org.project;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<Product> products;
    private final List<Shop> shops;
    private final List<Customer> customers;

    public Database() {
        this.products = new ArrayList<>();
        this.shops = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }

        products.add(product);
    }

    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("The shop cannot be null");
        }

        this.shops.add(shop);
        System.out.printf("Database: Shop %s by %s added!%n", shop.getName(), shop.getOwnerName());
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("The customer cannot be null");
        }

        this.customers.add(customer);
        System.out.printf("Database: Customer %s added!%n", customer.getFullName());
    }

    public Shop findShopByName(String name) {
        return this.shops.stream()
                .filter(shop -> shop.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getAllAvailableProducts() {
        List<Product> allAvailableProducts = new ArrayList<>();
        for (Shop shop : shops) {
            allAvailableProducts.addAll(shop.getProducts());
        }
        return allAvailableProducts;
    }
}
