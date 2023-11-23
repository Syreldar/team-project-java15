package org.project.entities.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.project.entities.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopDTO {
    private Long id;

    @NotBlank(message = "Shop name cannot be blank")
    @Size(max = 100, message = "Shop name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Shop owner name cannot be blank")
    @Size(max = 100, message = "Shop owner name cannot be longer than 100 characters")
    private String ownerName;

    private List<Product> products = new ArrayList<>();

    public ShopDTO() {}

    public ShopDTO(String name, String ownerName) {
        this();
        this.name = name;
        this.ownerName = ownerName;
    }

    public ShopDTO(String name, String ownerName, List<Product> products) {
        this.name = name;
        this.ownerName = ownerName;
        this.products = products != null ? products : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s]", this.name, this.ownerName);
    }
}
