<<<<<<< HEAD
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
=======
public class Product {

    String name;
    String manufacturer;
    double price;

    public String getName() {
        return name;
>>>>>>> 933e715 (added class Database and class Product)
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
<<<<<<< HEAD
        return this.manufacturer;
=======
        return manufacturer;
>>>>>>> 933e715 (added class Database and class Product)
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

<<<<<<< HEAD
    public BigDecimal getPrice() {
        return this.price;
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
=======
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
>>>>>>> 933e715 (added class Database and class Product)
}
