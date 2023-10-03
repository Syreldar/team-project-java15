package org.project;

import java.util.Objects;

public class Product {
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;
    private Category category;
    private float discountPercent;

    public Product(Category category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discountPercent = 0.0f;
    }

    public Product(Category category, String name, double price, int quantity, float discount) {
        this(category, name, price, quantity);
        this.discountPercent = discount;
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
        double discount = this.discountPercent / 100;
        double discountedPrice = this.price * discount;
        return this.price -= discountedPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        if (discountPercent >= 0.0f && discountPercent <= 100.0) {
            this.discountPercent = discountPercent;
        } else {
            throw new IllegalArgumentException("Invalid discount percentage. Must be between 0 and 100.");
        }
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