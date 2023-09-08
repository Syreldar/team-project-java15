package org.project;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private int quantity;
    private Category category;

    private BigDecimal discount;

    public Product(Category category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.quantity = quantity;
    }

    public Product(Category category, String name, double price, int quantity, BigDecimal discount) {
        this(category, name, price, quantity);
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
        return applyDiscount();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public BigDecimal applyDiscount() {
        return this.price.subtract(this.price.multiply(this.discount));
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