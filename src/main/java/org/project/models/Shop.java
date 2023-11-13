package org.project.models;

import jakarta.persistence.*;
import org.project.interfaces.Reviewable;

import java.util.*;

@Entity
@Table(name = "shops")
public class Shop implements Reviewable<ShopReview> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_name")
    private String name;

    @Column(name = "owner_name")
    private String ownerName;

    @ManyToMany(mappedBy = "shops")
    private List<Product> products;

    @OneToMany(mappedBy = "reviewedShop")
    private List<ShopReview> reviews;

    public Shop() {}

    public Shop(String name, String ownerName, List<Product> products) {
        this.name = name;
        this.ownerName = ownerName;
        this.products = products;
        this.reviews = new ArrayList<>();
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

    @Override
    public List<ShopReview> getReviews() {
        return reviews;
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
