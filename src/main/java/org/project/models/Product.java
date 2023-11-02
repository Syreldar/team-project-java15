package org.project.models;

import org.project.database.Database;
import org.project.interfaces.Reviewable;
import org.project.interfaces.Storable;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Objects;

//@Entity
public class Product implements Storable, Reviewable<ProductReview> {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@JsonIgnore
    private Category category;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;
    //@ManyToOne
    private List<ProductReview> reviews;

    public Product() {}

    public Product(Category category, String name, double price, int quantity) {
        if (category == null) {
            throw new IllegalArgumentException("The category cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("The name of the product cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("The price must be >= 0");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be > 0");
        }

        this.category = category;
        this.name = name;
        this.manufacturer = null;
        this.price = price;
        this.quantity = quantity;
        this.reviews = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("You can't set a negative quantity");
        }
        this.quantity = quantity;
    }

    public void reduceQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("The amount cannot be null or <= 0");
        }

        this.quantity = Math.max(0, this.quantity - amount);
    }

    public void reduceQuantity() {
        this.quantity -= 1;
    }

    @Override
    public List<ProductReview> getReviews() {
        return reviews;
    }

    @Override
    public void addReview(ProductReview review) {
        reviews.add(review);
    }

    @Override
    public int getReviewCount() {
        return reviews.size();
    }

    @Override
    public double getReviewsAverage() {
        OptionalDouble average = reviews.stream().mapToDouble(ProductReview::getRating).average();
        return average.orElse(0.0);
    }

    @Override
    public void register(Database database) {
        database.registerProduct(this);
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
                this.name, this.category, this.price, this.quantity);
    }
}
