package org.project.entities.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.project.entities.cart.Cart;
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

    @ManyToMany(mappedBy = "shops")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "reviewedShop")
    private List<ShopReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Cart> carts = new ArrayList<>();

    public Shop() {}

    public Shop(String name, String ownerName) {
        this();
        this.name = name;
        this.ownerName = ownerName;
    }

    public Shop(String name, String ownerName, List<Product> products, List<ShopReview> reviews, List<Cart> carts) {
        this();
        this.name = name;
        this.ownerName = ownerName;
        this.products = products;
        this.reviews = reviews;
        this.carts = carts;
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
        this.products.clear();
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public void removeCart(Cart cart) {
        carts.remove(cart);
    }

    public void addCarts(List<Cart> carts) {
        this.carts.addAll(carts);
    }

    public void removeCarts(List<Cart> carts) {
        this.carts.removeAll(carts);
    }

    public void clearCarts() {
        this.carts.clear();
    }

    @Override
    public List<ShopReview> getReviews() {
        return reviews;
    }

    @Override
    public void setReviews(List<ShopReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public void addReview(ShopReview review) {
        reviews.add(review);
    }

    @Override
    public void removeReview(ShopReview review) {
        reviews.remove(review);
    }

    @Override
    public void addReviews(List<ShopReview> reviews) {
        this.reviews.addAll(reviews);
    }

    @Override
    public void removeReviews(List<ShopReview> reviews) {
        this.reviews.removeAll(reviews);
    }

    @Override
    public void clearReviews() {
        this.reviews.clear();
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
