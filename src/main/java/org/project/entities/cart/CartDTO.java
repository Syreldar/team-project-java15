package org.project.entities.cart;

import jakarta.validation.constraints.NotNull;
import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.shop.Shop;

import java.util.List;

public class CartDTO {
    private Long id;

    @NotNull
    private List<Product> products;

    @NotNull
    private Shop shop;

    @NotNull
    private Customer customer;

    public CartDTO() {
    }

    public CartDTO(long id, List<Product> products, Shop shop, Customer customer) {
        this.id = id;
        this.products = products;
        this.shop = shop;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}