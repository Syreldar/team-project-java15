package org.project.models.dtos;

import jakarta.validation.constraints.*;

public class ProductDTO {

    private Long id;

    @NotBlank
    private String category; // Category is an enum and can be represented as a string

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String manufacturer;

    @NotNull
    @PositiveOrZero
    private double price;

    @NotNull
    @PositiveOrZero
    private int quantity = 1;

    public ProductDTO() {}

    public ProductDTO(String category, String name, String manufacturer, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.manufacturer = null;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryString() {
        return this.category;
    }

    public void setCategoryString(String categoryString) {
        this.category = categoryString;
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
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ProductDTO [Name: %s, Category: %s, Price: %.2f, Quantity: %d]",
                this.name, this.category, this.price, this.quantity);
    }
}
