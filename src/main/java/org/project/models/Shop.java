package org.project.models;

import org.project.database.Database;
import org.project.interfaces.Reviewable;
import org.project.interfaces.Storable;

import java.util.*;

//@Entity
public class Shop implements Storable, Reviewable<ShopReview> {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String ownerName;
    //@ManyToOne
    private List<Product> products;
    //@ManyToOne
    private List<ShopReview> reviews;

    public Shop() {}

    public Shop(String name, String ownerName, List<Product> products) {
        this.name = name;
        this.ownerName = ownerName;
        this.products = products;
        this.reviews = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
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
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("The quantity cannot be over the product's quantity");
        }

        handleTransaction(product, quantity);
        updateSalesStatistics(product);
        //incrementTotalSales();

        System.out.printf("%s: Sold %d unit%s of %s to customer %s. Remaining units: %d;%n",
                this.name, quantity, quantity > 1 ? "s" : "", product.getName(), customer.getFullName(),
                product.getQuantity());
        return true;
    }

    private void handleTransaction(Product product, int quantity) {
        product.reduceQuantity(quantity);
        //this.totalGains += product.getPrice() * quantity;
    }

    private void updateSalesStatistics(Product product) {
        Category productCategory = product.getCategory();
        //int currentSales = this.categorySales.getOrDefault(productCategory, 0);
        //this.categorySales.put(productCategory, currentSales + 1);
    }

    /*
    public Category getMostSoldCategory() {
        return categorySales.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    */

    public Product findProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ShopReview> getReviews() {
        return reviews;
    }

    @Override
    public void addReview(ShopReview review) {
        reviews.add(review);
    }

    @Override
    public int getReviewCount() {
        return reviews.size();
    }

    @Override
    public double getReviewsAverage() {
        OptionalDouble average = reviews.stream().mapToDouble(ShopReview::getRating).average();
        return average.orElse(0.0);
    }

    @Override
    public void register(Database database) {
        database.registerShop(this);
    }

    @Override
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s]", this.name, this.ownerName);
    }
    /*
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s, TotalGains: %.2f, MostSoldCategory: %s]",
                this.name, this.ownerName, this.totalGains,
                getMostSoldCategory() != null ? getMostSoldCategory() : "None");
    }
    */
}
