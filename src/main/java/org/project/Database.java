
package org.project;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Database {
    private final List<Shop> shops;
    private final List<Customer> customers;
    private final HashMap<Shop, List<Product>> sales;

    public Database() {
        this.shops = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.sales = new HashMap<>();
    }

    public void addShop(Shop shop) {
        this.shops.add(shop);
        System.out.printf("Database: Shop %s by %s added!%n", shop.getName(), shop.getOwnerName());
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        System.out.printf("Database: Customer %s added!%n", customer.getFullName());
    }

    public void addSales(Shop sellerShop, Product product) {
        this.sales.computeIfAbsent(sellerShop, k -> new ArrayList<>()).add(product);
    }

    public Shop findShopByName(String name) {
        return this.shops.stream()
                .filter(shop -> shop.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}