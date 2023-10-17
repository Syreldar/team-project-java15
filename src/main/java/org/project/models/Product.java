package org.project.models;

import org.project.database.Database;
import org.project.charts.Chart;
import org.project.interfaces.Storable;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Objects;

public class Product implements Storable {
    private Category category;
    private String name;
    private String manufacturer;
    private double price;
    private final int initialQuantity;
    private int currentQuantity;
    private final List<ProductReview> reviews;

    public Product(Category category, String name, double price, int initialQuantity) {
        if (category == null) {
            throw new IllegalArgumentException("The category cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("The name of the product cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("The price must be >= 0");
        }
        if (initialQuantity <= 0) {
            throw new IllegalArgumentException("The quantity must be > 0");
        }

        this.category = category;
        this.name = name;
        this.manufacturer = null;
        this.price = price;
        this.initialQuantity = this.currentQuantity = initialQuantity;
        this.reviews = new ArrayList<>();
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("You can't set a negative price");
        }
        this.price = price;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public int getCurrentQuantity() {
        return this.currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        if (currentQuantity < 0) {
            throw new IllegalArgumentException("You can't set a negative quantity");
        }
        this.currentQuantity = currentQuantity;
    }

    public void reduceQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("The amount cannot be null or <= 0");
        }

        this.currentQuantity = Math.max(0, this.currentQuantity - amount);
    }

    public int getSoldAmount() {
        return this.initialQuantity - this.currentQuantity;
    }

    public void reduceQuantity() {
        this.currentQuantity -= 1;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void addReview(ProductReview review) {
        reviews.add(review);
    }

    public int getReviewCount() {
        return reviews.size();
    }

    public double getReviewsAverage() {
        OptionalDouble average = reviews.stream().mapToDouble(Review::getRating).average();
        return average.orElse(0.0);
    }

    @Override
    public void register(Database database, Chart chart) {
        database.registerProduct(this);
        chart.addProduct(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("Product [Name: %s, Category: %s, Price: %.2f, Quantity: %d]",
                this.name, this.category, this.price, this.currentQuantity);
    }
}
