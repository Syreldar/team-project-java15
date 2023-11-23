package org.project.entities.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.project.entities.cart.Cart;
import org.project.entities.shop.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "surname", nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    @PositiveOrZero
    private double balance = 0.0;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @NotBlank
    @Column(nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "customer_shop",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id")
    )
    private List<Shop> shops = new ArrayList<>();

    public Customer() {}

    public Customer(String name, String lastName, double balance, String address, String email) {
        this();
        this.name = name;
        this.lastName = lastName;
        this.balance = balance;
        this.address = address;
        this.email = email;
    }

    public Customer(String name, String lastName, double balance, String address, String email, List<Cart> carts, List<Shop> shops) {
        this(name, lastName, balance, address, email);
        this.carts = carts != null ? carts : new ArrayList<>();
        this.shops = shops != null ? shops : new ArrayList<>();
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

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        cart.setCustomer(this);
        this.carts.add(cart);
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
    }

    public void addCarts(List<Cart> carts) {
        for (Cart cart : carts) {
            cart.setCustomer(this);
        }
        this.carts.addAll(carts);
    }

    public void removeCarts(List<Cart> carts) {
        this.carts.removeAll(carts);
    }

    public void clearCarts() {
        this.carts.clear();
    }

    public List<Shop> getShops() {
        return shops;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Customer customer = (Customer) o;
        return Double.compare(customer.balance, balance) == 0 &&
                Objects.equals(name, customer.name) &&
                Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, balance);
    }

    @Override
    public String toString() {
        return String.format("Customer [Name: %s, Surname: %s, Balance: %.2f, Address: %s, Email: %s]",
                this.name, this.lastName, this.balance, this.address, this.email);
    }
}
