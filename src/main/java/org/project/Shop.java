package org.project;

import java.util.*;

public class Shop implements Storable {
    private String name;
    private String ownerName;
    private double totalGains = 0.0;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getTotalGains() {
        return totalGains;
    }

    public void setTotalGains(double totalGains) {
        this.totalGains = totalGains;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void incrementTotalSales() {
        totalSales++;
    }

    public void addGains(double gains) {
        totalGains += gains;
    }

    public boolean containsProduct(Product product) {
        return products.stream().anyMatch(p -> p.equals(product));
    }

    public boolean containsProduct(String productName) {
        return products.stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(productName));
    }

    public boolean sellProduct(Customer customer, Product product, int quantity) {
        if (customer == null) {
            throw new IllegalArgumentException("The customer cannot be null");
        }
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity cannot be <= 0");
        }
        if (quantity > product.getCurrentQuantity()) {
            throw new IllegalArgumentException("The quantity cannot be over the product's quantity");
        }

        handleTransaction(product, quantity);
        updateSalesStatistics(product);
        incrementTotalSales();

        System.out.printf("%s: Sold %d unit%s of %s to customer %s. Remaining units: %d;%n",
                this.name, quantity, quantity > 1 ? "s" : "", product.getName(), customer.getFullName(),
                product.getCurrentQuantity());
        return true;
    }

    private void handleTransaction(Product product, int quantity) {
        product.reduceQuantity(quantity);
        this.totalGains += product.getPrice() * quantity;
    }

    private void updateSalesStatistics(Product product) {
        Category productCategory = product.getCategory();
        int currentSales = this.categorySales.getOrDefault(productCategory, 0);
        this.categorySales.put(productCategory, currentSales + 1);
    }

    public Category getMostSoldCategory() {
        return categorySales.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Product findProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
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
        return products.stream()
                .max(Comparator.comparingInt(Product::getSoldAmount))
                .orElse(null);
    }

    @Override
    public void register(Database database, Chart chart) {
        database.addShop(this);
        chart.addShop(this);
    }

    @Override
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s, TotalGains: %.2f, MostSoldCategory: %s]",
                this.name, this.ownerName, this.totalGains,
                getMostSoldCategory() != null ? getMostSoldCategory() : "None");
    }
}
