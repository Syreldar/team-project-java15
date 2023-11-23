package org.project.entities.cart;

import jakarta.persistence.*;
import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.shop.Shop;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;


    @PrePersist
    private void initializeCustomer() {
        if (this.customer != null) {
            this.customer.addCart(this);
        }
    }

    public Cart() {}

    public Cart(Shop shop, Customer customer) {
        this();
        this.shop = shop;
        this.customer = customer;
    }

    public Cart(Shop shop, Customer customer, List<Product> products) {
        this(shop, customer);
        this.products = products != null ? products : new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    public void clearProducts() {
        products.clear();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public double calculateTotalAmount() throws RuntimeException, IllegalArgumentException {
        if (products == null) {
            throw new IllegalArgumentException("Product list is null");
        }

        if (products.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = 0.0;

        for (Product product : products) {
            if (product == null) {
                throw new IllegalArgumentException("Cart contains a null product");
            }

            if (product.getPrice() == null) {
                throw new IllegalArgumentException("Cart contains a product which price is null");
            }

            totalAmount += product.getPrice();
        }

        return totalAmount;
    }

    public int getTotalProductsInCart() {
        int totalQuantity = 0;
        for (Product product : this.products) {
            totalQuantity += product.getQuantity();
        }
        return totalQuantity;
    }
    public void clear() {
        if (this.products == null || this.products.isEmpty()) {
            throw new RuntimeException("Cart is already empty");
        }

        this.products.clear();
    }
}
