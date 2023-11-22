package org.project.entities.cart;

import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.shop.Shop;

import java.util.List;

public class CartDTO {
    private Long id;
    private List<Product> items;
    private Shop shop;
    private Customer customer;

    public CartDTO() {
    }

    public CartDTO(long id, List<Product> items, Shop shop, Customer customer) {
        this.id = id;
        this.items = items;
        this.shop = shop;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
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