package org.project.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.project.review.interfaces.Reviewable;
import org.project.review.productreview.ProductReview;
import org.project.shop.Shop;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product implements Reviewable<ProductReview> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String manufacturer;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("0.10")
    private double price;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("1")
    private int quantity = 1;

    @NotNull
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(
            name = "product_to_shop",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id")
    )
    private List<Shop> shops;

    @OneToMany(mappedBy = "reviewedProduct")
    private List<ProductReview> reviews;

    public Product() {}

    public Product(Category category, String name, double price, int quantity, List<Shop> shops, List<ProductReview> reviews) {
        this.category = category;
        this.name = name;
        this.manufacturer = null;
        this.price = price;
        this.quantity = quantity;
        this.shops = shops;
        this.reviews = reviews;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (price < 0) {
            throw new IllegalArgumentException("You can't set a negative price");
        }
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("You can't set a negative quantity");
        }
        this.quantity = quantity;
    }

    public void reduceQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("The amount cannot be null or <= 0");
        }

        this.quantity = Math.max(0, this.quantity - amount);
    }

    public void reduceQuantity() {
        this.quantity -= 1;
    }

    public List<Shop> getShops() {
        return this.shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void addShop(Shop shop) {
        this.shops.add(shop);
    }

    @Override
    public List<ProductReview> getReviews() {
        return reviews;
    }

    @Override
    public void addReview(ProductReview review) {
        reviews.add(review);
    }

    @Override
    public int getReviewCount() {
        return reviews.size();
    }

    @Override
    public double getReviewsAverage() {
        OptionalDouble average = reviews.stream().mapToDouble(ProductReview::getRating).average();
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

        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("Product [Name: %s, Category: %s, Price: %.2f, Quantity: %d]",
                this.name, this.category, this.price, this.quantity);
    }
}
