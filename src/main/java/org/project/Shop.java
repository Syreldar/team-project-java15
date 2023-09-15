package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public class Shop implements Storable {
    private String name;
    private String ownerName;
    private BigDecimal totalGains = BigDecimal.ZERO;
    private final List<Product> products;
    private final Map<Category, Integer> categorySales;
    private int totalSales = 0;
    private final List<Review> reviews;

    public Shop(String name, String ownerName, List<Product> products) {
        this.name = name;
        this.ownerName = ownerName;
        this.products = new ArrayList<>(products);
        this.categorySales = new HashMap<>();
        this.reviews = new ArrayList<>();
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
        return products.stream().anyMatch(p -> p.equals(product));
    }

    public boolean containsProduct(String productName) {
        return products.stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(productName));
    }

    public void sellProduct(Customer customer, Product product, int quantity) {
        if (customer == null) {
            throw new IllegalArgumentException("The customer cannot be null");
        }
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity cannot be <= 0");
        }

        // Handle financial transaction and inventory
        this.handleTransaction(product, quantity);

        // Update the sales statistics
        this.updateSalesStatistics(product);

        // Increment total sales
        this.incrementTotalSales();

        System.out.printf("%s: Sold %d unit%s of %s to customer %s. Remaining units: %d;%n", this.name, quantity, quantity > 1 ? "s" : "", product.getName(), customer.getFullName(), product.getCurrentQuantity());
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

    public void addReview(Review review) {
        reviews.add(review);
    }

    public int getReviewCount() {
        return reviews.size();
    }

    public double getReviewsAverage() {
        OptionalDouble average = reviews.stream().mapToDouble(Review::getRating).average();
        return average.orElse(0.0);
    }

    public Product getMostSoldProduct() {
        Product mostSoldProduct = null;
        int maxSoldAmount = 0;

        for (Product product : products) {
            int soldAmount = product.getSoldAmount();
            if (soldAmount > maxSoldAmount) {
                maxSoldAmount = soldAmount;
                mostSoldProduct = product;
            }
        }

        return mostSoldProduct;
    }

    @Override
    public void register(Database database, Chart chart) {
        database.addShop(this);
        chart.addShop(this);
    }

    @Override
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s, TotalGains: %.2f, MostSoldCategory: %s]",
                this.name, this.ownerName, this.totalGains, getMostSoldCategory() != null ? getMostSoldCategory() : "None");
    }
}
