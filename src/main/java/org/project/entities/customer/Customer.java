package org.project.entities.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.project.entities.cart.Cart;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;



    @PrePersist
    private void initializeCart() {
        if (this.cart == null) {
            this.cart = new Cart();
            this.cart.setCustomer(this);
        }
    }

    public Customer() {}

    public Customer(String name, String lastName, double balance, String address, String email, Cart cart) {
        this.name = name;
        this.lastName = lastName;
        this.balance = balance;
        this.address = address;
        this.email = email;
        this.cart=cart;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
