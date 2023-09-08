package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    private String name;
    private String ownerName;
    private BigDecimal totalGains = BigDecimal.ZERO;
    private final List<Product> products;
    private final Map<Category, Integer> categorySales;
    private int totalSales = 0;

    public Shop(String name, String ownerName, List<Product> products) {
        this.name = name;
        this.ownerName = ownerName;
        this.products = new ArrayList<>(products);
        this.categorySales = new HashMap<>();
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwner(String owner) {
        this.ownerName = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalGains() {
        return this.totalGains;
    }

    public void setTotalGains(BigDecimal totalGains) {
        this.totalGains = totalGains;
    }

    public int getTotalSales() {
        return this.totalSales;
    }

    public void incrementTotalSales() {
        this.totalSales++;
    }

    public void addGains(BigDecimal currentGains) {
        this.totalGains = this.totalGains.add(currentGains);
    }

    public boolean containsProduct(Product product) {
        for (Product p : this.products) {
            if (p.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void sellProduct(Customer customer, Product product, int quantity) {
        // Handle financial transaction and inventory
        this.handleTransaction(product, quantity);

        // Update the sales statistics
        this.updateSalesStatistics(product);

        // Increment total sales
        this.incrementTotalSales();

        System.out.printf("%s: Sold %d unit%s of %s to customer %s. Remaining units: %d;%n", this.name, quantity, quantity > 1 ? "s" : "", product.getName(), customer.getFullName(), product.getQuantity());
    }

    private void handleTransaction(Product product, int quantity) {
        product.reduceQuantity(quantity);
        this.addGains(product.getPrice().multiply(new BigDecimal(quantity)));
    }

    private void updateSalesStatistics(Product product) {
        Category productCategory = product.getCategory();
        int currentSales = this.categorySales.getOrDefault(productCategory, 0);
        this.categorySales.put(productCategory, currentSales + 1);
    }

    public Category getMostSoldCategory() {
        int maxSales = -1;
        Category mostSold = null;
        for (Map.Entry<Category, Integer> entry : this.categorySales.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                mostSold = entry.getKey();
            }
        }
        return mostSold;
    }

    public Product findProductByName(String name) {
        for (Product product : this.products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s, TotalGains: %.2f, MostSoldCategory: %s]",
                this.name, this.ownerName, this.totalGains, getMostSoldCategory());
    }
}
