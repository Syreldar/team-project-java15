package org.project.entities.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.project.entities.cart.Cart;
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
    private List<Product> products;

    @OneToMany(mappedBy = "reviewedShop")
    private List<ShopReview> reviews;

    @OneToMany(mappedBy = "shop")
    private List<Cart> carts;




    @PrePersist
    private void initializeCollections() {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        if (this.carts == null) {
            this.carts = new ArrayList<>();
        }
    }

    public Shop() {
        initializeCollections();
    }

    public Shop(String name, String ownerName) {
        this();
        this.name = name;
        this.ownerName = ownerName;
    }

    public Shop(String name, String ownerName, List<Product> products, List<ShopReview> reviews, List<Cart> carts) {
        this();
        this.name = name;
        this.ownerName = ownerName;
        this.products = products != null ? products : new ArrayList<>();
        this.reviews = reviews != null ? reviews : new ArrayList<>();
        this.carts = carts != null ? carts : new ArrayList<>();
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

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public List<ShopReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ShopReview> reviews) {
        this.reviews = reviews;
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
