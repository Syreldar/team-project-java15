package org.project.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.project.entities.cart.Cart;
import org.project.entities.review.interfaces.Reviewable;
import org.project.entities.review.productreview.ProductReview;
import org.project.entities.shop.Shop;

import java.util.*;

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
    private Double price;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("1")
    private int quantity = 1;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @NotNull
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "product_to_shop",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id")
    )
    private List<Shop> shops = new ArrayList<>();

    @OneToMany(mappedBy = "reviewedProduct")
    private List<ProductReview> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Cart> carts = new ArrayList<>();

    public Product() {}

    public Product(Category category, String name, String manufacturer, Double price, int quantity) {
        this();
        this.category = category;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(Category category, String name, String manufacturer, Double price, int quantity,
                   List<Shop> shops, List<ProductReview> reviews, List<Cart> carts)
    {
        this(category, name, manufacturer, price, quantity);
        this.shops = shops;
        this.reviews = reviews;
        this.carts = carts;
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

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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

    public void removeShop(Shop shop) {
        this.shops.remove(shop);
    }

    public void addShops(List<Shop> shops) {
        this.shops.addAll(shops);
    }

    public void removeShops(List<Shop> shops) {
        this.shops.removeAll(shops);
    }

    public void clearShops() {
        this.shops.clear();
    }

    @Override
    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public void addReview(ProductReview review) {
        reviews.add(review);
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
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
