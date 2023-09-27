package org.project;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private int quantity;
    private Category category;

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
        this.price = BigDecimal.valueOf(price);
        this.quantity = quantity;
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
            this.quantity -= 1;
        } else {
            this.quantity = Math.max(0, this.quantity - amount);
        }
    }

    public void reduceQuantity() {
        this.quantity -= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
