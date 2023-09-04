package org.project;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    private String owner;
    private String name;
    private double totalGains;
    List<Product> products;
    private Map<Category, Integer> categorySales;

    public Shop(String owner, String name, List<Product> products) {
        this.owner = owner;
        this.name = name;
        this.products = products;
        this.categorySales = new HashMap<>();
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalGains() {
        return this.totalGains;
    }

    public void setTotalGains(double totalGains) {
        this.totalGains = totalGains;
    }

    public void addGains(double currentGains) {
        this.totalGains += currentGains;
    }

    public boolean containsProduct(Product product) {
        for (Product p : this.products) {
            if (p.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void sellProduct(Product product, double price) {
        // Handle financial transaction and inventory
        handleTransaction(product, price);

        // Update the sales statistics
        updateSalesStatistics(product);
    }

    private void handleTransaction(Product product, double price) {
        this.products.remove(product);
        addGains(price);
    }

    private void updateSalesStatistics(Product product) {
        Category productCategory = product.getCategory();
        int currentSales = categorySales.getOrDefault(productCategory, 0);
        categorySales.put(productCategory, currentSales + 1);
    }

    public Category getMostSoldCategory() {
        int maxSales = -1;
        Category mostSold = null;
        for (Map.Entry<Category, Integer> entry : categorySales.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                mostSold = entry.getKey();
            }
        }
        return mostSold;
    }
}

