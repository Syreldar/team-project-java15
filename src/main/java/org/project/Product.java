package org.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class Product implements Storable {
    private Category category;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private BigDecimal discount;
    private int initialQuantity;
    private int currentQuantity;
    private final List<Review> reviews;

    public Product(Category category, String name, double price, int quantity, BigDecimal discount) {
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
        this.price = BigDecimal.valueOf(price);
        this.currentQuantity = quantity;
        this.reviews = new ArrayList<>();
        this.discount = discount;
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("You can't set a negative price");
        }
        this.price = price;
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
            this.currentQuantity -= 1;
        } else {
            this.currentQuantity = Math.max(0, this.currentQuantity - amount);
        }
    }
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getSoldAmount() {
        return this.initialQuantity - this.currentQuantity;
    }

    public void reduceQuantity() {
        this.currentQuantity -= 1;
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
    public BigDecimal applyDiscount() {
        return this.price.subtract(this.price.multiply(this.discount));
    }

    @Override
    public void register(Database database, Chart chart) {
        database.addProduct(this);
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
}
