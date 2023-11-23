package org.project.entities.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.project.entities.customer.Customer;
import org.project.entities.product.Product;
import org.project.entities.review.interfaces.Reviewable;
import org.project.entities.review.shopreview.ShopReview;

import java.util.*;

@Entity
@Table(name = "shops")
public class Shop implements Reviewable<ShopReview> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "shop_name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @OneToMany(mappedBy = "shop")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "reviewedShop")
    private List<ShopReview> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "shops")
    private List<Customer> customers = new ArrayList<>();

    public Shop() {}

    public Shop(String name, String ownerName) {
        this();
        this.name = name;
        this.ownerName = ownerName;
    }

    public Shop(String name, String ownerName, List<Product> products, List<ShopReview> reviews, List<Customer> customers) {
        this();
        this.name = name;
        this.ownerName = ownerName;
        this.products = products;
        this.reviews = reviews;
        this.customers = customers;
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

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        product.setShop(this);
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
        this.products.clear();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void addCustomers(List<Customer> customers) {
        this.customers.addAll(customers);
    }

    public void removeCustomers(List<Customer> customers) {
        this.customers.removeAll(customers);
    }

    public void clearCustomers() {
        this.customers.clear();
    }

    @Override
    public List<ShopReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ShopReview> reviews) {
        this.reviews = reviews;
    }

    public void addReviews(List<ShopReview> reviews) {
        this.reviews.addAll(reviews);
    }

    public void removeReviews(List<ShopReview> reviews) {
        this.reviews.removeAll(reviews);
    }

    public void clearReviews() {
        this.reviews.clear();
    }

    @Override
    public void addReview(ShopReview review) {
        reviews.add(review);
    }

    @Override
    public int getReviewCount() {
        return reviews.size();
    }

    @Override
    public double getReviewsAverage() {
        OptionalDouble average = reviews.stream().mapToDouble(ShopReview::getRating).average();
        return average.orElse(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) && Objects.equals(ownerName, shop.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ownerName);
    }

    @Override
    public String toString() {
        return String.format("Shop [Name: %s, OwnerName: %s]", this.name, this.ownerName);
    }
}
